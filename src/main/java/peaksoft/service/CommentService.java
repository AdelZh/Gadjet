package peaksoft.service;

import peaksoft.request.CommentRequest;
import peaksoft.response.CommentResponse;

public interface CommentService {

    CommentResponse saveComment(CommentRequest commentRequest);
}
