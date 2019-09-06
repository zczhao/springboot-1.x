package zzc.springboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zzc.springboot.entity.Girl;
import zzc.springboot.serivce.GirlService;

/**
 * 测试Service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTests {

    @Autowired
    private GirlService girlService;

    @Test
    public void testFindOne() {
        Girl girl = girlService.findOne(3);
        Assert.assertEquals(Integer.valueOf(13), girl.getAge());
    }
}
