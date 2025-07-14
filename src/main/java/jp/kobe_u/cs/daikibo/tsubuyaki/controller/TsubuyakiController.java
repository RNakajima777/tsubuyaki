package jp.kobe_u.cs.daikibo.tsubuyaki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import jp.kobe_u.cs.daikibo.tsubuyaki.service.TsubuyakiService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
  
public class TsubuyakiController {

    @Autowired
    TsubuyakiService ts;
  
    //タイトル画面を表示
    @GetMapping("/")
    String showIndex() {  
        return "index";
    }
  
    //メイン画面を表示
    @GetMapping("/read")
    String showTsubuyakiList(Model model) {

        List<Tsubuyaki> list = ts.getAllTsubuyaki(); //全つぶやきを取得  

        model.addAttribute("tsubuyakiList", list);   //モデル属性にリストをセット
        model.addAttribute("tsubuyakiForm", new TsubuyakiForm());  //空フォームをセット

        return "tsubuyaki_list"; //リスト画面を返す 
    }

    //検索結果を表示
    @GetMapping("/search")
    String searchTsubuyaki(@RequestParam(name = "keyword") String keyword, Model model) {
        List<Tsubuyaki> list = ts.searchTsubuyaki(keyword);
        model.addAttribute("tsubuyakiList", list);
        return "search_result";
    }

  
    //つぶやきを投稿  
    @PostMapping("/read")  
    String postTsubuyaki(@ModelAttribute("tsubuyakiForm") TsubuyakiForm form, Model model) {

        //フォームからエンティティに移し替え
        Tsubuyaki t = new Tsubuyaki();  
        t.setName(form.getName());
        t.setComment(form.getComment());

        //サービスに投稿処理を依頼
        ts.postTsubuyaki(t);

        return "redirect:/read"; //メイン画面に転送
  
    }
}
