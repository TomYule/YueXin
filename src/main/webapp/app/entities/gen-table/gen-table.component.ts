import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IGenTable } from '@/shared/model/gen-table.model';
import AlertMixin from '@/shared/alert/alert.mixin';
import JhiDataUtils from '@/shared/data/data-utils.service';
import GenTableService from './gen-table.service';

import ImportTable from "./gen-import-table.vue";

import hljs from "highlight.js/lib/common";
import "highlight.js/styles/github-dark.css";
import JhiMetricsModal from "@/admin/metrics/metrics-modal.vue";

hljs.registerLanguage("java", require("highlight.js/lib/languages/java"));
hljs.registerLanguage("xml", require("highlight.js/lib/languages/xml"));
hljs.registerLanguage("html", require("highlight.js/lib/languages/xml"));
hljs.registerLanguage("vue", require("highlight.js/lib/languages/xml"));
hljs.registerLanguage("javascript", require("highlight.js/lib/languages/javascript"));
hljs.registerLanguage("sql", require("highlight.js/lib/languages/sql"));

@Component({
  components: {
    'importTable':ImportTable,
  },
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
  public multipleSelection = [];

  // 预览参数
  public preview = {
    open: false,
    title: "代码预览",
    data: {},
    activeName: "domain.java"
  }

  data() {
    return {
      pickerOptions: {
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
      dateRange: '',
      queryParams: {
        tableName: undefined,
        tableComment: undefined
      },

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

  /** 预览按钮 */
  public handlePreview(row) {
    this.genTableService()
      .previewCode(row.id)
      .then(res => {
        this.preview.data = res;
        this.preview.open = true;
      });
  };

  /** 高亮显示 */
  public highlightedCode(code, key) {
    const vmName = key.substring(key.lastIndexOf("/") + 1, key.indexOf(".vm"));
    var language = vmName.substring(vmName.indexOf(".") + 1, vmName.length);
    const result = hljs.highlight(language, code || "", true);
    return result.value || '&nbsp;';
  };

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
    if ((<any>this.$refs.importTable).show) {
      (<any>this.$refs.importTable).show();
    }
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
