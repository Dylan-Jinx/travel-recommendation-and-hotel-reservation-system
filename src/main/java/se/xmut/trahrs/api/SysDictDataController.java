package se.xmut.trahrs.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import se.xmut.trahrs.common.ApiResponse;
import se.xmut.trahrs.domain.model.SysDictType;
import se.xmut.trahrs.log.annotation.WebLog;
import se.xmut.trahrs.service.SysDictDataService;
import se.xmut.trahrs.domain.model.SysDictData;
import se.xmut.trahrs.service.SysDictTypeService;


/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/sysDictData")
public class SysDictDataController {

    final Logger logger = LoggerFactory.getLogger(SysDictDataController.class);
    @Autowired
    private SysDictDataService sysDictDataService;
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @WebLog(description = "添加字典数据表")
    @PostMapping("/save/{name}")
    public ApiResponse save(@RequestBody SysDictData sysDictData, @PathVariable String name) {
        //通过type表的type名来查出uuid
        QueryWrapper<SysDictType> typeWrapper = new QueryWrapper<>();
        typeWrapper.select("dict_type");
        typeWrapper.eq("dict_name", name);
        String uuid = sysDictTypeService.getOne(typeWrapper).getDictType();
        sysDictData.setDictType(uuid);
        //通过uuid来count出当前有几条记录来设定sort
        QueryWrapper<SysDictData> dataWrapper = new QueryWrapper<>();
        dataWrapper.eq("dict_type", uuid);
        int line = (int) sysDictDataService.count(dataWrapper);
        sysDictData.setDictSort(line);
        sysDictData.setCreateBy("breeze");
        sysDictData.setCreateTime(LocalDateTime.now());
        return ApiResponse.ok(sysDictDataService.saveOrUpdate(sysDictData));
    }

    @WebLog(description = "用id删除字典数据表")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictDataService.removeById(id));
    }

    @WebLog(description = "查询全部字典数据表")
    @GetMapping
    public ApiResponse findAll() {
        return ApiResponse.ok(sysDictDataService.list());
    }

    @WebLog(description = "用id查找字典数据表")
    @GetMapping("/{id}")
    public ApiResponse findOne(@PathVariable Integer id) {
        return ApiResponse.ok(sysDictDataService.getById(id));
    }

    @WebLog(description = "分页字典数据表")
    @GetMapping("/page")
    public ApiResponse findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return ApiResponse.ok(sysDictDataService.page(new Page<>(pageNum, pageSize)));
    }

}

