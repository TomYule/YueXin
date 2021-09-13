import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IGenTable } from '@/shared/model/gen-table.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import JhiDataUtils from '@/shared/data/data-utils.service';

import GenTableService from './gen-table.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class GenTable extends mixins(JhiDataUtils, AlertMixin) {
  @Inject('genTableService') private genTableService: () => GenTableService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  // 显示搜索条件
  public showSearch = true;
  // 日期范围
  public dateRange: "";
  // 查询参数
  public  queryParams=  {
    tableName: undefined,
    tableComment: undefined
  };

  data() {
    return {
      pickerOptions2: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      value6: '',
      value7: ''
    };
  }

  public genTables: IGenTable[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllGenTables();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllGenTables();
  }

  public retrieveAllGenTables(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.genTableService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.genTables = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IGenTable): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeGenTable(): void {
    this.genTableService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('yueXinApp.genTable.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllGenTables();
        this.closeDialog();
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }
  public  handleSizeChange(val) {
    console.log(`每页 ${val} 条`);
    this.itemsPerPage =val;
    this.transition();
  }

  public handleCurrentChange(val) {
    console.log(`当前页: ${val}`);
    this.page = val;
    this.transition();
  }
  /** 生成代码操作 */
  public handleGenTable(row) {
    // const tableNames = row.tableName || this.tableNames;
    // if (tableNames == "") {
    //   this.msgError("请选择要生成的数据");
    //   return;
    // }
    // if(row.genType === "1") {
    //   genCode(row.tableName).then(response => {
    //     this.msgSuccess("成功生成到自定义路径：" + row.genPath);
    //   });
    // } else {
    //   downLoadZip("/tool/gen/batchGenCode?tables=" + tableNames, "ruoyi");
    // }
  };
  /** 打开导入表弹窗 */
  public openImportTable() {
    // this.$refs.import.show();
  };

  /** 修改按钮操作 */
  public handleEditTable(row) {
    // const tableId = row.tableId || this.ids[0];
    // this.$router.push({ path: '/tool/gen-edit/index', query: { tableId: tableId, pageNum: this.queryParams.pageNum } });
  };

  /** 删除按钮操作 */
  public handleDelete(row) {
    // const tableIds = row.tableId || this.ids;
    // this.$confirm('是否确认删除表编号为"' + tableIds + '"的数据项?', "警告", {
    //   confirmButtonText: "确定",
    //   cancelButtonText: "取消",
    //   type: "warning"
    // }).then(function() {
    //   return delTable(tableIds);
    // }).then(() => {
    //   this.getList();
    //   this.msgSuccess("删除成功");
    // }).catch(() => {});
  }

  /** 重置按钮操作 */
  public resetQuery() {
    // this.dateRange = [];
    // this.resetForm("queryForm");
    this.transition();
  }
  public transition(): void {
    this.retrieveAllGenTables();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
