import { TestBed, inject } from '@angular/core/testing';

import { TablemanagerService } from './tablemanager.service';

describe('TablemanagerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TablemanagerService]
    });
  });

  it('should be created', inject([TablemanagerService], (service: TablemanagerService) => {
    expect(service).toBeTruthy();
  }));
});
