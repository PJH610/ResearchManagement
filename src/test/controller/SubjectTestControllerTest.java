package controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring-mvc.xml"})
@WebAppConfiguration
@ActiveProfiles("dev")
public class SubjectTestControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    // 这个方法在每个方法执行之前都会执行一遍
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); // 初始化MockMvc对象
    }
    @Test
    public void testString()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/add") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("name","zhong")
                        .param("host_id","1")
                        .param("status","1")
                        .param("classification_id","1")
                        .param("category_id","1")
                        .param("final_result","fgg")
                        .param("grants","1")
                        .param("financialcategory","CATEGORY_A")
                        .param("complete_time","2014-12-4")
                        .param("bankcard","jkjkjkj")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString13()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/update") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","10")
                        .param("name","liang")
                        .param("status","2")
                        .param("finalresult","fgg")
                        .param("grants","1")
                        .param("financialcategory","CATEGORY_A")
                        .param("completetime","2014-12-4")
                        .param("bankcard","jkjkjkj")
                        .param("project_number","5454")
                        .param("project_reference","5878")
                        .param("conclusion_number","8787")
                        .param("subject_classification","4545454")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString14()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/deleteBatch") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","9")
                        .param("id","8")
        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString15()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectFeasibility/delete") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","5")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString16()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectFeasibility/update") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","1")
                        .param("achievement","hh")
                        .param("task","kk")
                        .param("requirement","kk")
        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString1()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectPersonnel/add") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("subject_id","4")
                        .param("name","jj")
                        .param("age","11")
                        .param("unit","jj")
                        .param("task","kk")
                        .param("position","hh")
                        .param("education","jjkjlk")
        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString17()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectProof/deleteBatch") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","3")
                        .param("id","4")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString2()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectPersonnel/update") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("subject_id","3")
                        .param("name","bb")
                        .param("age","11")
                        .param("unit","pp")
                        .param("task","pp")
                        .param("position","pp")
                        .param("education","ppppp")
        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString3()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectPersonnel/delete") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","2")
                        .param("id","3")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString4()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectRecommender/add") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("subject_id","3")
                        .param("name","hh")
                        .param("position","kk")
                        .param("profession","kk")
                        .param("unit","hh")
                        .param("subname","hh")
                        .param("recommend","hh")
        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString18()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectRecommender/delete") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","6")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString19()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectRecommender/update") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","1")
                        .param("name","hh")
                        .param("position","kk")
                        .param("profession","kk")
                        .param("unit","hh")
                        .param("subname","hh")
                        .param("recommend","hh")
        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }


    @Test
    public void testString10()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectProof/delete") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","6")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString5()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectFeasibility/insert") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("subject_id","5")
                        .param("achievement","hh")
                        .param("task","kk")
                        .param("requirement","kk")
        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString6()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectSchedule/add") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("subject_id","4")
                        .param("start_time","2104-12-4")
                        .param("end_time","2018-5-22")
                        .param("content","jjjj")
                        .param("name","fff")
                        .param("host","ppp")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString7()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectSchedule/update") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","1")
                        .param("start_time","2014-12-4")
                        .param("end_time","2017-5-22")
                        .param("content","pppp")
                        .param("name","fff")
                        .param("host","ppp")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString8()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectSchedule/delete") // 请求的url，请求的方法是get
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","5")


        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test//测试tb_subjects_funds插入
    public void testString9()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectsFunds/add") // 请求的url，请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("subject_id","3")
                        .param("data","1")
                        .param("travel","10.00")
                        .param("meeting","10.00")
                        .param("equipment","10.00")
                        .param("service","10.11")
                        .param("print","10.00")
                        .param("identification","10.00")
                        .param("other","10.00")
                        .param("funding","11.10")
                        .param("selfraised","11.10")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    // 测试tb_subjects_funds修改
    @Test
    public void testString11()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subjectsFunds/update") // 请求的url，请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("subject_id","5")
                        .param("data","10.06")
                        .param("travel","10.00")
                        .param("meeting","10.00")
                        .param("equipment","10.00")
                        .param("service","10.11")
                        .param("print","10.00")
                        .param("identification","10.00")
                        .param("other","10.00")
                        .param("funding","11.10")
                        .param("selfraised","11.10")


        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    // 测试tb_subjects_funds修改
    @Test
    public void testString20()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/specialist/add") // 请求的url，请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("name","1")
                        .param("user_id","1")
                        .param("audit_opinion","ok")


        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString21()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/specialist/list") // 请求的url，请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("name","1")
                        .param("user_id","1")
                        .param("audit_opinion","ok")


        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString22()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/approval/update") // 请求的url，请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","1")
                        .param("department_notes","3232")
                        .param("leader_name","787898")
                        .param("district_notes","dfasfdaf")
                        .param("distric_name","gsgsag")
                        .param("municipal_notes","sfasdfasdf")
                        .param("municipal_name","dfsfaf")
        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString23()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/approval/delete") // 请求的url，请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","2")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString24()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/result/add") // 请求的url，请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("subject_id","3")
                        .param("completetime","2014/5/5")
                        .param("finalresult_name","ssss")
                        .param("finalresult_shape","dvdvdv")
                        .param("principal","erer")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    @Test
    public void testString25()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/result/delete") // 请求的url，请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("id","2")

        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }

    // 测试tb_subjects_Medium插入
    @Test
    public void testString30()throws Exception {
        String responseString = mockMvc.perform(
                post("/api/subject/medium/update") // 请求的url，请求的方法是post
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) // 数据的格式
                        .param("subject_id","4")
                        .param("changes","2")
                        .param("adjust","false")
                        .param("adjust_reason","2")
                        .param("situation","2")
                        .param("funds","5.00")
                        .param("supporting_funds","1")
                        .param("used_funds","1")
                        .param("funds_detail","2")
                        .param("file_id","14")
        )
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("-----返回的json = " + responseString);
    }


}
