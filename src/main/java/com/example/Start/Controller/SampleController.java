package com.example.Start.Controller;

import com.example.Start.model.request.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class SampleController {
    //갯 방식의 요청
    @GetMapping("/01helloWorld") //클라이언트의 주소 요청 템플릿을 요청한다.
    public String helloWorldGet1() {
        return "01helloWorld"; //html파일이나 mustache로 이동한다.
    }

    //주소 쳐서 들어오는 갯 방식.. (리퀘스트 맵핑은 예전)
    //사용자 요청을 받을 수 있는 폼 만들기
    // 로그인 화면 요청하면 사용자 화면을 보여주고 제출 버튼 만들어보기.
    //컨트롤러 만들고, 템플릿에 리턴값 이름 같게 만들어주기
    @RequestMapping(value = "/02loginInput", method = RequestMethod.GET)
    public String helloWorldGet2() {
        return "02loginInput";
    }


    //@RequestMapping(value = "/03loginOutput", method = RequestMethod.POST)
    @PostMapping("/03loginOutPut") //날라올 때 쿼리스트링으로 네임과 패스워드를 받음
    public String loginOut(
            @RequestParam(value = "name", required = false) String name,
            //네임이 없어도 동작할 수 있게 required 쓰임
            @RequestParam String password,
            Model model) {
        System.out.println(name);
        System.out.println(password);

        model.addAttribute("name",name);
        //앞에 있는 네임을 담아 뒤에 있는 네임 값을 불러올 수 있음
        model.addAttribute("password",password);

        return "03loginOutPut"; //입력한 값을 보여주는 화면. model 필요
    }

    @GetMapping("04loginInput")
    public String loginDtoInput(){
        return "/04loginInput";
    }

    @PostMapping("/05loginOutPut")
    public String loginDtoOutput(LoginDto dto, Model model) {
        System.out.println(dto);
        model.addAttribute("dto",dto);
        return "/05loginOutPut";
    }

    //모델을 배열에 담아서 해보기
    @GetMapping("/06arrayList")
    public ModelAndView arrayList() {
        ArrayList<LoginDto> dtos=new ArrayList<>();
        dtos.add(new LoginDto("1","1"));
        dtos.add(new LoginDto("2","2"));
        dtos.add(new LoginDto("3","3"));
        ModelAndView model = new ModelAndView();
        model.addObject("dtos",dtos);
        model.setViewName("06OutPut");
        return model;
    }

    @GetMapping("/07main")
    public String getMain() {
        return "07main";
    }
}
