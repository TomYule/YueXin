/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_FORMAT } from '@/shared/date/filters';
import SysDictDataService from '@/entities/sys-dict-data/sys-dict-data.service';
import { SysDictData } from '@/shared/model/sys-dict-data.model';

const mockedAxios: any = axios;
const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn(),
}));

describe('Service Tests', () => {
  describe('SysDictData Service', () => {
    let service: SysDictDataService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new SysDictDataService();
      currentDate = new Date();

      elemDefault = new SysDictData(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            createTime: format(currentDate, DATE_FORMAT),
            upLocalDate: format(currentDate, DATE_FORMAT),
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a SysDictData', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createTime: format(currentDate, DATE_FORMAT),
            upLocalDate: format(currentDate, DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate,
            upLocalDate: currentDate,
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a SysDictData', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a SysDictData', async () => {
        const returnedFromService = Object.assign(
          {
            dictSort: 'BBBBBB',
            dictLabel: 'BBBBBB',
            dictValue: 'BBBBBB',
            dictType: 'BBBBBB',
            cssClass: 'BBBBBB',
            listClass: 'BBBBBB',
            isDefault: 'BBBBBB',
            status: 'BBBBBB',
            createBy: 'BBBBBB',
            createTime: format(currentDate, DATE_FORMAT),
            updateBy: 'BBBBBB',
            upLocalDate: format(currentDate, DATE_FORMAT),
            remark: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createTime: currentDate,
            upLocalDate: currentDate,
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a SysDictData', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of SysDictData', async () => {
        const returnedFromService = Object.assign(
          {
            dictSort: 'BBBBBB',
            dictLabel: 'BBBBBB',
            dictValue: 'BBBBBB',
            dictType: 'BBBBBB',
            cssClass: 'BBBBBB',
            listClass: 'BBBBBB',
            isDefault: 'BBBBBB',
            status: 'BBBBBB',
            createBy: 'BBBBBB',
            createTime: format(currentDate, DATE_FORMAT),
            updateBy: 'BBBBBB',
            upLocalDate: format(currentDate, DATE_FORMAT),
            remark: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate,
            upLocalDate: currentDate,
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of SysDictData', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a SysDictData', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a SysDictData', async () => {
        mockedAxios.delete.mockReturnValue(Promise.reject(error));

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
