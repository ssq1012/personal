package com.example.demo.service.Impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.ConsumerGoodsMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.ConsumerGoods;
import com.example.demo.pojo.Student;
import com.example.demo.service.TestService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    ConsumerGoodsMapper consumerGoodsMapper;

    @Override
    public String getStringThing() {
        return "测试String类型事件";
    }

    static ThreadPoolExecutor poolExecutor =  new ThreadPoolExecutor(3,3,5L, TimeUnit.MINUTES,new ArrayBlockingQueue(200000));

    @Override
    public String getStudent(String name) {
        Student selectById = studentMapper.selectById(1L);
        return selectById.toString();
    }

    @Override
    public String getGoods(String brand) {
        JSONObject result = new JSONObject();
        Map map = new HashMap<>();
        map.put("brand",brand);
        List<ConsumerGoods> list = consumerGoodsMapper.selectByMap(map);
        result.put("data",list);
        result.put("code","200");
        return result.toJSONString();
    }

    @Override
    @SneakyThrows
    public void createStudent(String count) {
        int total = Integer.parseInt(count);
        Student student = new Student();
        for (int i = 0; i <total; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    String sex = randSex();
                    int age = randAge();
                    String familyName = randFamilyName();
                    String name = randomName(sex);
                    student.setStuName(familyName.concat(name));
                    student.setAge(age);
                    student.setSex(sex);
                    try {
                        student.setCommets(randComments(30));
                    }catch (Exception e){
                        System.out.println("异常"+ e.getMessage());
                    }
                    studentMapper.insert(student);
                }
            });
        }
    }

    @Override
    public void exportStudent(String count) {
        StopWatch stopWatch =  new StopWatch();
        stopWatch.start();
        String filePath = "D:\\文档存放\\students.xlsx";
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("id",10000);
        IPage<Student> page = new Page<>(1,1000);
        List<Student> students = studentMapper.selectPage(page,queryWrapper).getRecords();
        EasyExcel.write(filePath,Student.class).sheet("第一页").doWrite(students);
        stopWatch.stop();
        System.out.println("该次导出耗时:" + stopWatch.getTotalTimeSeconds() + "s");
    }

    private String randomName(String sex) {
        String name = "";
        int randNum = new Random().nextInt(2) + 1;
        int index;
        if (sex.equals("男")) {
            int strLen = boyName.length();
            if (randNum % 2 == 0) {
                index = new Random().nextInt(strLen - 1);
                name = boyName.substring(index, index + randNum);
            } else {
                index = new Random().nextInt(strLen);
                name = boyName.substring(index, index + randNum);
            }
        } else {
            int strLen = girlName.length();
            if (randNum % 2 == 0) {
                index = new Random().nextInt(strLen - 1);
                name = girlName.substring(index, index + randNum);
            } else {
                index = new Random().nextInt(strLen);
                name = girlName.substring(index, index + randNum);
            }
        }
        return name;
    }

    public String randSex() {
        int randNum = new Random().nextInt(2) + 1;
        return randNum == 1 ? "男" : "女";
    }

    public String randFamilyName() {
        String str = "";
        int strLen;
        int randNum = new Random().nextInt(2) + 1;
        int index;
        if (randNum == 1) {
            strLen = familyName.length();
            index = new Random().nextInt(strLen);
            str = String.valueOf(familyName.charAt(index));
        } else {
            strLen = firstName2.length();
            index = new Random().nextInt(strLen);
            if (index % 2 == 0) {
                str = firstName2.substring(index, index + 2);
            } else {
                str = firstName2.substring(index - 1, index + 1);
            }
        }
        return str;
    }

    public int randAge() {
        return new Random().nextInt(10) + 18;
    }


    public String randComments(int count)throws Exception{
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            String randomWord = getRandomWord(new Random().nextInt());
            builder.append(randomWord);
        }
        return builder.toString();
    }

    public String getRandomWord(long seed) throws UnsupportedEncodingException {
        int highPos;
        int lowPos;      //高位、低位
        Random random = new Random(seed);      //随机数生成器
        highPos = 176 + Math.abs(random.nextInt(39));        //计算高位数
        lowPos = 161 + Math.abs(random.nextInt(93));      //计算低位数
        byte[] b = new byte[2];      //转化为B类型
        b[0] = (new Integer(highPos)).byteValue();            //高字节
        b[1] = (new Integer(lowPos)).byteValue();         //低字节
        return new String(b, "GBK");
    }

    final static String familyName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻水云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳鲍史唐费岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅卞齐康伍余元卜顾孟平"
            + "黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计成戴宋茅庞熊纪舒屈项祝董粱杜阮席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田胡凌霍万柯卢莫房缪干解应宗丁宣邓郁单杭洪包诸左石崔吉"
            + "龚程邢滑裴陆荣翁荀羊甄家封芮储靳邴松井富乌焦巴弓牧隗山谷车侯伊宁仇祖武符刘景詹束龙叶幸司韶黎乔苍双闻莘劳逄姬冉宰桂牛寿通边燕冀尚农温庄晏瞿茹习鱼容向古戈终居衡步都耿满弘国文东殴沃曾关红游盖益桓公晋楚闫";
    final static String firstName2 = "欧阳太史端木上官司马东方独孤南宫万俟闻人夏侯诸葛尉迟公羊赫连澹台皇甫宗政濮阳公冶太叔申屠公孙慕容仲孙钟离长孙宇文司徒鲜于司空闾丘子车亓官司寇巫马公西颛孙壤驷公良漆雕乐正宰父谷梁拓跋夹谷轩辕令狐段干百里呼延东郭南门羊舌微生公户公玉公仪梁丘公仲公上公门公山公坚左丘公伯西门公祖第五公乘贯丘公皙南荣东里东宫仲长子书子桑即墨达奚褚师吴铭";
    final static  String girlName = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽";
    final static String boyName = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
}
