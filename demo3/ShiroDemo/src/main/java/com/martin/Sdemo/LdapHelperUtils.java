package com.martin.Sdemo;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class LdapHelperUtils {
    
    public static final String ATTR_USER_ID = "uid";
    public static final String ATTR_ALIAS_USER_ID = "userid";
    
    public static final String ATTR_COMMON_NAME = "cn";
    public static final String ATTR_ALIAS_COMMON_NAME = "commonName";
    
    public static final String ATTR_GIVEN_NAME = "gn";
    public static final String ATTR_ALIAS_GIVEN_NAME = "givenName";

    public static final String ATTR_LAST_NAME = "sn";
    public static final String ATTR_ALIAS_LAST_NAME = "surname";
    
    public static final String ATTR_EMAIL = "mail";
    public static final String ATTR_ALIAS_EMAIL = "rfc822Mailbox";
    
    public static final String ATTR_ALIAS_PHONE = "telephoneNumber";
    
    public static final String ATTR_ALIAS_ADDRESS = "postalAddress";

    public static final String ATTR_STREET = "street";
    public static final String ATTR_ALIAS_STREET = "streetAddress";
    
    public static final String ATTR_CITY = "l";
    public static final String ATTR_ALIAS_CITY = "localityName";
    
    public static final String ATTR_STATE = "st";
    public static final String ATTR_ALIAS_STATE = "stateOrProvinceName";
    
    public static final String ATTR_COUNTRY = "co";
    public static final String ATTR_ALIAS_COUNTRY = "friendlyCountryName";
    
    public static final String ATTR_POSTAL_CODE = "postalCode";
    
    public static String getAttributeValueAsString(Attributes attrs, String... keys ){
        if (attrs == null || attrs.size() == 0) return null;
        Attribute attr = null;
        Object value = null;
        
        for (String key : keys){
            attr = attrs.get(key);
            if (attr == null) continue;
            try {
                value = attr.get();
            } catch (NamingException e) {
                continue;
            }
            if (value != null) break;
        }
        
        return value == null ? null : value.toString();
    }

    public static UserInfo buildUserInfo(Attributes attrs){
        if (attrs == null || attrs.size() == 0) return null;
        UserInfo info = new UserInfo();
        String value = getAttributeValueAsString(attrs, ATTR_USER_ID, ATTR_ALIAS_USER_ID);
        if (value != null) info.setUserId(value);
        value = getAttributeValueAsString(attrs, ATTR_COMMON_NAME, ATTR_ALIAS_COMMON_NAME);
        if (value != null) info.setNickname(value);
        value = getAttributeValueAsString(attrs, ATTR_GIVEN_NAME, ATTR_ALIAS_GIVEN_NAME);
        if (value != null) info.setFirstName(value);
        value = getAttributeValueAsString(attrs, ATTR_LAST_NAME, ATTR_ALIAS_LAST_NAME);
        if (value != null) info.setLastName(value);
        value = getAttributeValueAsString(attrs, ATTR_ALIAS_USER_ID);
        if (value != null) info.setPreferredUsername(value);
        value = getAttributeValueAsString(attrs, ATTR_EMAIL, ATTR_ALIAS_EMAIL);
        if (value != null) info.setEmail(value);
        value = getAttributeValueAsString(attrs, ATTR_ALIAS_PHONE);
        if (value != null) info.setPhoneNumber(value);
        
        return info;
    }

}
