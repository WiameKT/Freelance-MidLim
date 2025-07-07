package net.feedbacky.app.service.comment;

import net.feedbacky.app.data.idea.dto.comment.FetchCommentDto;
import net.feedbacky.app.data.idea.dto.comment.PatchCommentDto;
import net.feedbacky.app.data.idea.dto.comment.PostCommentDto;
import net.feedbacky.app.data.idea.dto.comment.reaction.FetchCommentReactionDto;
import net.feedbacky.app.data.idea.dto.comment.reaction.PostCommentReactionDto;
import net.feedbacky.app.data.user.dto.FetchUserDto;
import net.feedbacky.app.util.PaginableRequest;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Plajer
 * <p>
 * Created at 14.10.2019
 */
public interface CommentService {

  PaginableRequest<List<FetchCommentDto>> getAllForIdea(long ideaId, int page, int pageSize, SortType sortType);

  FetchCommentDto getOne(long id);

  ResponseEntity<FetchCommentDto> post(PostCommentDto dto);

  FetchCommentReactionDto postReaction(long id, PostCommentReactionDto dto);

  FetchCommentDto patch(long id, PatchCommentDto dto);

  ResponseEntity delete(long id);

  ResponseEntity deleteReaction(long id, String reactionId);

  enum SortType {
    NEWEST, OLDEST
  }

}
