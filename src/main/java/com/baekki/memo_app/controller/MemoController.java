package com.baekki.memo_app.controller;

import com.baekki.memo_app.repository.MemoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemoController {
    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @GetMapping("/")
    public String listMemos(Model model) {
        model.addAttribute("memos", memoRepository.findAll());

        return "memo-list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable int id,
            Model model
    ) {
        model.addAttribute("memo", memoRepository.findById(id));

        return "memo-edit";
    }

    @PostMapping("/add")
    public String addMemo(
            @RequestParam String title,
            @RequestParam String content
    ) {
        memoRepository.save(title, content);

        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editMemo(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam String content
    ) {
        memoRepository.update(id, title, content);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteMemo(@RequestParam int id) {
        memoRepository.delete(id);

        return "redirect:/";
    }
}