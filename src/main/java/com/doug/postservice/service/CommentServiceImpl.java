package com.doug.postservice.service;

import com.doug.postservice.feign.comment.CommentClient;
import com.doug.postservice.feign.comment.model.HolderComment;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentClient commentClient;

    @Override
    public List<HolderComment> getFilteredComments(Map<String, String> filterParams) {
        List<HolderComment> holderComments = commentClient.getAllComments();
        if (filterParams.isEmpty()) {
            return holderComments;
        }
        Predicate<HolderComment> byFilter = comment -> {
            boolean isFilter = true;
            for(Map.Entry<String, String> entry: filterParams.entrySet()){
                try {
                    isFilter = isFilter && (BeanUtils.getProperty(comment, entry.getKey()).equals(entry.getValue()));
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    isFilter = false;
                }
            }
            return isFilter;
        };
        return holderComments.stream().filter(byFilter).collect(Collectors.toList());
    }
}
