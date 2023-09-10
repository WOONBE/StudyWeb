package StudyWeb.controller.post;
import StudyWeb.controller.ResponseErrorDto;
import StudyWeb.domain.StudyGroup;
import StudyWeb.domain.post.Chat;
import StudyWeb.exception.PostNotFoundException;
import StudyWeb.repository.PostRepository;
import StudyWeb.service.PostService;
import StudyWeb.status.GroupStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/community")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping
    private ResponseEntity home() {
        return ResponseEntity.ok().body("커뮤니티 홈 테스트");
    }

    @GetMapping("/chats/size")
    public ResponseEntity<?> getChatsSize() {
        try {
            int chatSize = postService.getAllChatSize();
            return ResponseEntity.ok(chatSize);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    @GetMapping("/studies/size")
    public ResponseEntity<?> getStudiesSize() {
        try {
            int studiesSize = postService.getAllStudiesSize();
            return ResponseEntity.ok(studiesSize);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

//    @GetMapping("/questions/size")
//    public ResponseEntity<?> getQuestionsSize() {
//        try {
//            int questionsSize = postService.getAllQuestionsSize();
//            return ResponseEntity.ok(questionsSize);
//        }catch (Exception e) {
//            e.printStackTrace();
//            ResponseErrorDto errorDto = ResponseErrorDto.builder()
//                    .error(e.getMessage())
//                    .build();
//            return ResponseEntity.badRequest().body(errorDto);
//        }
//    }

    //자유 게시판 리스트 get
    @GetMapping("/chats")
    public ResponseEntity<?> getChats(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(value = "order", required = false) String order,
            @RequestParam(value = "s", required = false) String s) {
        try {
            List<PostResponseDto> chats = postService.findAllChats(pageable, order, s).getContent();
            return ResponseEntity.ok(chats);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    //스터디 게시판 리스트 get
    @GetMapping("/studies")
    public ResponseEntity<?> getStudies(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(value = "status", required = false) GroupStatus status,
            @RequestParam(value = "order", required = false) String order,
            @RequestParam(value = "tags", required = false) List<String> tags,
            @RequestParam(value = "s", required = false) String s
    ) {
        try {
            List<String> upperTags = Optional.ofNullable(tags).orElseGet(Collections::emptyList).stream().map(String::toUpperCase).collect(Collectors.toList());
            List<PostResponseDto> studies = postService.findAllStudies(pageable, status, order, upperTags, s).getContent();
            return ResponseEntity.ok(studies);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    //질문 게시판 리스트 get
//    @GetMapping("/questions")
//    public ResponseEntity<?> getQuestions(
//            @PageableDefault(size = 10)Pageable pageable,
//            @RequestParam(value = "status",required = false) QuestionStatus status,
//            @RequestParam(value = "order",required = false) String order,
//            @RequestParam(value = "tags",required = false) List<String> tags,
//            @RequestParam(value = "s",required = false)String s
//    ) {
//        try {
//            List<String> upperTags = Optional.ofNullable(tags).orElseGet(Collections::emptyList).stream().map(String::toUpperCase).collect(Collectors.toList());
//            List<PostResponseDto> questions = postService.findAllQuestions(pageable,status,order,upperTags,s).getContent();
//            return ResponseEntity.ok(questions);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ResponseErrorDto errorDto = ResponseErrorDto.builder()
//                    .error(e.getMessage())
//                    .build();
//            return ResponseEntity.badRequest().body(errorDto);
//        }
//    }

    //자유 게시판 게시글 조회 By Post ID
    @GetMapping("/chats/{id}")
    public ResponseEntity<?> getChatById(@PathVariable("id") Long id) {
        try {
            PostResponseDto chat = postService.findChatById(id);
            return ResponseEntity.ok().body(chat);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    //스터디 게시판 게시글 조회 By Post ID
    @GetMapping("/studies/{id}")
    public ResponseEntity<?> getStudyById(@PathVariable("id") Long id) {
        try {
            PostResponseDto chat = postService.findStudyById(id);
            return ResponseEntity.ok().body(chat);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    //질문 게시판 게시글 조회 By Post ID
//    @GetMapping("/questions/{id}")
//    public ResponseEntity<?> getQuestionById(@PathVariable("id") Long id) {
//        try {
//            PostResponseDto chat = postService.findQuestionById(id);
//            return ResponseEntity.ok().body(chat);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ResponseErrorDto errorDto = ResponseErrorDto.builder()
//                    .error(e.getMessage())
//                    .build();
//            return ResponseEntity.badRequest().body(errorDto);
//        }
//    }

    /*
     * Controller에서 객체 생성 하지 않는 방향
     * */
    //자유 게시판 글 작성
    @PostMapping("/chat")
    public ResponseEntity<?> createChat(PostRequestCreateDto requestPostDto) {
        try {
            PostResponseDto responseDto = postService.createChat(requestPostDto);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    //스터디 게시판 글 작성
    @PostMapping("/study")
    public ResponseEntity<?> createStudy(PostRequestCreateDto requestPostDto) {
        try {
            PostResponseDto responseDto = postService.createStudy(requestPostDto);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

//    //질문 게시판 글 작성
//    @PostMapping("/question")
//    public ResponseEntity<?> createQuestion(PostRequestCreateDto requestPostDto) {
//        try {
//            PostResponseDto responseDto = postService.createQuestion(requestPostDto);
//            return ResponseEntity.ok(responseDto);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ResponseErrorDto errorDto = ResponseErrorDto.builder()
//                    .error(e.getMessage())
//                    .build();
//            return ResponseEntity.badRequest().body(errorDto);
//        }
//    }

    //chat update
    @PatchMapping("/chat/{id}")
    public ResponseEntity<?> updateChat(@PathVariable("id") Long id, PostRequestUpdateDto updateDto) {
        try {
            postService.updateChat(id, updateDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    //study update
    @PatchMapping("/study/{id}")
    public ResponseEntity<?> updateStudy(@PathVariable("id") Long id, PostRequestUpdateDto updateDto) {
        try {
            postService.updateStudy(id, updateDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    //qna update
//    @PatchMapping("/question/{id}")
//    public ResponseEntity<?> updateQuestion(@PathVariable("id") Long id, PostRequestUpdateDto updateDto) {
//        try {
//            postService.updateQuestion(id, updateDto);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            ResponseErrorDto errorDto = ResponseErrorDto.builder()
//                    .error(e.getMessage())
//                    .build();
//            return ResponseEntity.badRequest().body(errorDto);
//        }
//    }

    @DeleteMapping("/chat/{id}")
    public ResponseEntity<?> deleteChat(@PathVariable("id") Long id) {
        try {
            Chat chat = (Chat) postRepository.findById(id).orElseThrow(PostNotFoundException::new);
            postService.deleteChat(chat);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }

    @DeleteMapping("/study/{id}")
    public ResponseEntity<?> deleteStudy(@PathVariable("id") Long id) {
        try {
            StudyGroup study = (StudyGroup) postRepository.findById(id).orElseThrow(PostNotFoundException::new);
            postService.deleteStudy(study);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseErrorDto errorDto = ResponseErrorDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorDto);
        }
    }
}

//    @DeleteMapping("/question/{id}")
//    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id) {
//        try {
//            Question question = (Question) postRepository.findById(id).orElseThrow(PostNotFoundException::new);
//            postService.deleteQuestion(question);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            ResponseErrorDto errorDto = ResponseErrorDto.builder()
//                    .error(e.getMessage())
//                    .build();
//            return ResponseEntity.badRequest().body(errorDto);
//        }
//    }
//}
