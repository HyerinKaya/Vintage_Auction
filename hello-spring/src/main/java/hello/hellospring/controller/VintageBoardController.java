package hello.hellospring.controller;


import hello.hellospring.domain.Item;
import hello.hellospring.domain.VintageBoard;
import hello.hellospring.dto.VintageBordForm;
import hello.hellospring.service.ItemService;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.VintageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class VintageBoardController {

    private final VintageService vintageService;


    // CREATE - 중고등록 로직 -> 등록이 성공적으로 완료되면 main 페이지로 이동한다.
    @PostMapping("/api/vintage/new")
    public ResponseEntity<?> createVintage(@Valid @ModelAttribute VintageBordForm vintageForm,
                                           BindingResult bindingResult,
                                           HttpServletRequest request) throws Exception {

        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, Object> result = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                result.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        //세션에 저장된 로그인 한 회원 정보 가져오기
        HttpSession session = request.getSession();
        Long memberId = (Long)session.getAttribute("memberNo");

        VintageBoard saveVintageBoard = vintageService.save(vintageForm, memberId);
        return new ResponseEntity<>(saveVintageBoard,HttpStatus.OK);
    }

    //READ - 중고상품목록 조회 -> 번호, 제목, 작성자Id 보여지기
    @GetMapping("/api/vintages")
    public ResponseEntity<?> vintageList(Model model) {
        List<VintageBoard> vintageBoards = vintageService.findAll();
        model.addAttribute("vintageBoards", vintageBoards);
        return new ResponseEntity<>(vintageBoards, HttpStatus.OK);
    }

    //READ - 중고상품 상세 조회 -> 제목 / 아이템명, 아이템 가격, 아이템 이미지 / 설명
    @GetMapping("/api/vintage/{vintageBoardId}")
    public ResponseEntity<?> vintageDetail(@PathVariable("vintageBoardId") Long vintageBoardId) {
        Optional<VintageBoard> findVintageBoard = vintageService.findById(vintageBoardId);
        VintageBoard vintageBoard = findVintageBoard.get();

        Map<String, Object> result = new HashMap<>();
        result.put("title", vintageBoard.getVintageTitle());
        result.put("detail", vintageBoard.getVintageDetail());
        result.put("itemName", vintageBoard.getVintageItem().getItemName());
        result.put("itemPrice", vintageBoard.getVintageItem().getItemPrice());
        result.put("itemCategory", vintageBoard.getVintageItem().getItemCategory());
        result.put("itemImages", vintageBoard.getVintageItem().getUploadFiles());
        result.put("memberId", vintageBoard.getMember().getMemberId());
        result.put("createdTime", vintageBoard.getCreatedTime());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //UPDATE - 중고상품 업데이트
    @PostMapping("/api/vintage/{vintageBoardId}/edit")
    public ResponseEntity<?> updateVintage(@ModelAttribute VintageBordForm vintageForm, @PathVariable("vintageBoardId") Long vintageBoardId, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        Long memberNo = (Long)session.getAttribute("memberNo");
        VintageBoard updateVintageBoard = vintageService.update(vintageBoardId, vintageForm, memberNo);

        return new ResponseEntity<>(updateVintageBoard, HttpStatus.OK);

    }

    //DELETE - 중고상품 삭제
    @PostMapping("/api/vintage/{vintageBoardId}/delete")
    public ResponseEntity<?> deleteVintage(@PathVariable("vintageBoardId") Long vintageBoardId,
                                           HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Long memberNo = (Long)session.getAttribute("memberNo");

        vintageService.delete(vintageBoardId, memberNo);

        return new ResponseEntity<>("게시물 삭제 완료", HttpStatus.OK);
    }

}
