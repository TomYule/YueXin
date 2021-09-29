package com.yuexin.web.rest;

import com.yuexin.domain.GenTable;
import com.yuexin.service.GenTableService;
import com.yuexin.service.SysRoleService;
import com.yuexin.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing {@link com.yuexin.domain.GenTable}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GenTableResource {

    private final Logger log = LoggerFactory.getLogger(GenTableResource.class);

    private static final String ENTITY_NAME = "genTable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private GenTableService genTableService;

    /**
     * {@code POST  /gen-tables} : Create a new genTable.
     *
     * @param genTable the genTable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new genTable, or with status {@code 400 (Bad Request)} if the genTable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gen-tables")
    public ResponseEntity<GenTable> createGenTable(@Valid @RequestBody GenTable genTable) throws URISyntaxException {
        log.debug("REST request to save GenTable : {}", genTable);
        if (genTable.getId() != null) {
            throw new BadRequestAlertException("A new genTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GenTable result = genTableService.insert(genTable);
        return ResponseEntity.created(new URI("/api/gen-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gen-tables} : Updates an existing genTable.
     *
     * @param genTable the genTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated genTable,
     * or with status {@code 400 (Bad Request)} if the genTable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the genTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gen-tables")
    public ResponseEntity<GenTable> updateGenTable(@Valid @RequestBody GenTable genTable) throws URISyntaxException {
        log.debug("REST request to update GenTable : {}", genTable);
        if (genTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        genTableService.update(genTable);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, genTable.getId().toString()))
            .body(genTable);
    }

    /**
     * {@code GET  /gen-tables} : get all the genTables.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of genTables in body.
     */
    @GetMapping("/gen-tables")
    public ResponseEntity<List<GenTable>> getAllGenTables(Pageable pageable) {
        log.debug("REST request to get a page of GenTables");
        Page<GenTable> page = genTableService.query(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gen-tables} : get all the genTables.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of genTables in body.
     */
    @GetMapping("/gen-tables/db")
    public ResponseEntity<List<GenTable>> getDbGenTables(@RequestParam(value = "tableName" ,required = false) String tableName,
                                                         @RequestParam(value = "tableComment",required = false) String tableComment,
                                                         Pageable pageable) {
        log.debug("REST request to get a page of GenTables");
        Page<GenTable> page = genTableService.selectDbTableList(tableName, tableComment, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gen-tables/:id} : get the "id" genTable.
     *
     * @param id the id of the genTable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the genTable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gen-tables/{id}")
    public ResponseEntity<GenTable> getGenTable(@PathVariable Long id) {
        log.debug("REST request to get GenTable : {}", id);
        Optional<GenTable> genTable = Optional.ofNullable(genTableService.fetch(id));
        return ResponseUtil.wrapOrNotFound(genTable);
    }

    /**
     * {@code GET  /gen-tables/preview-code/:id} : get the "id" genTable.
     *
     * @param id the id of the genTable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the genTable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gen-tables/preview-code/{id}")
    public ResponseEntity<Map<String, String>> previewCode(@PathVariable Long id) {
        log.debug("REST request to get previewCode : {}", id);
        Optional<Map<String, String>> genTable = Optional.ofNullable(genTableService.previewCode(id));
        return ResponseUtil.wrapOrNotFound(genTable);
    }

    /**
     * {@code DELETE  /gen-tables/:id} : delete the "id" genTable.
     *
     * @param id the id of the genTable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gen-tables/{id}")
    public ResponseEntity<Void> deleteGenTable(@PathVariable Long id) {
        log.debug("REST request to delete GenTable : {}", id);
        genTableService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
