package zzc.springboot.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zzc.springboot.entity.Girl;
import zzc.springboot.enums.ResultEnum;
import zzc.springboot.exception.GirlException;
import zzc.springboot.repository.GirlRepository;

import javax.transaction.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);


        Girl girlB = new Girl();
        girlB.setCupSize("BBBB");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            // 返回"你还在上小学吧" code=100
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        } else if (age > 10 && age < 16) {
            // 返回"你可能在上初中" code=101
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }

        // 如果>16岁，加钱
        // ...

    }

    /**
     * 通过Id查询一个女生的信息
     *
     * @param id
     * @return
     */
    public Girl findOne(Integer id) {
        return girlRepository.findOne(id);
    }
}
