package com.cit.lifelong.service;

import com.cit.lifelong.dto.PaginationDTO;
import com.cit.lifelong.dto.QuestionDTO;
import com.cit.lifelong.mapper.QuestionMapper;
import com.cit.lifelong.mapper.UserMapper;
import com.cit.lifelong.model.Question;
import com.cit.lifelong.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName QuestionService
 * @Description: QuestionService
 * @Author hehen
 * @Date 2020/3/14
 * @Version V1.0
 **/
@Service
public class QuestionService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    public PaginationDTO getQuestionList(int page, int size) {
        int total = questionMapper.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(total,page,size);

        List<Question> questionList = questionMapper.list((page-1)*size,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question: questionList) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestionDTOList(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO getQuestionByCreator(int id, Integer page, Integer size) {
        int total = questionMapper.countByCreator(id);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(total,page,size);

        List<Question> questionList = questionMapper.listByCreator(id,(page-1)*size,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question: questionList) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestionDTOList(questionDTOList);

        return paginationDTO;
    }
}
