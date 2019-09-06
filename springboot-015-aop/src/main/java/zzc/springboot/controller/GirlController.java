package zzc.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zzc.springboot.entity.Girl;
import zzc.springboot.repository.GirlRepository;
import zzc.springboot.serivce.GirlService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        logger.info("GirlController.girlList()...");
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     * @return
     */
    @PostMapping(value = "/girls")
    public Girl girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        return girlRepository.save(girl);
    }

    //查询一个女生
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlRepository.findOne(id);
    }

    //更新
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);

        return girlRepository.save(girl);
    }

    //删除
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
    }

    //通过年龄查询女生列表
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

    @GetMapping(value = "girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{
        girlService.getAge(id);
    }
}
