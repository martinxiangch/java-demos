package com.martin.Sdemo;

import java.util.logging.Level;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.ldap.DefaultLdapRealm;
import org.apache.shiro.realm.ldap.LdapContextFactory;
import org.apache.shiro.realm.ldap.LdapUtils;


public class LdapRealm extends DefaultLdapRealm {

    @Override
    public boolean supports(AuthenticationToken token) {

        return super.supports(token);
    }

    protected AuthenticationInfo queryForAuthenticationInfo(AuthenticationToken token,
            LdapContextFactory ldapContextFactory) throws NamingException {


        Object principal = token.getPrincipal();
        Object credentials = token.getCredentials();

        System.out.println("Authenticating user "+principal+" through LDAP");

        principal = getLdapPrincipal(token);

        LdapContext ctx = null;
        try {
            ctx = ldapContextFactory.getLdapContext(principal, credentials);
            //context was opened successfully, which means their credentials were valid.  Return the AuthenticationInfo:
            
            UserInfo userInfo = loadUserInfoFromLdapNode(ctx, ""+token.getPrincipal());
            

            AuthenticationInfo info = new SimpleAuthenticationInfo(userInfo, token.getCredentials(), getName());
            return info;
        } finally {
            LdapUtils.closeContext(ctx);
        }
    }


    private UserInfo loadUserInfoFromLdapNode(LdapContext ctx, String accountName) {

        String searchFilter = "(uid=" + accountName + ")";
        String ldapSearchBase = getUserDnSuffix();
        if (ldapSearchBase != null && ldapSearchBase.trim().startsWith(",")) {
            ldapSearchBase = ldapSearchBase.substring(1);
        }

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        try {
            NamingEnumeration<SearchResult> results =
                    ctx.search(ldapSearchBase, searchFilter, searchControls);
            SearchResult searchResult = null;
            if (results.hasMoreElements()) {
                searchResult = (SearchResult) results.nextElement();

                // make sure there is not another item available, there should be only 1 match
                if (results.hasMoreElements()) {
                    System.out
                            .println("Matched multiple users for the accountName: " + accountName);
                    return null;
                }

                UserInfo info = LdapHelperUtils.buildUserInfo(searchResult.getAttributes());
                return info;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
