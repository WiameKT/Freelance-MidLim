package net.feedbacky.app.data.board.dto.webhook;

import lombok.Getter;
import net.feedbacky.app.data.FetchResponseDto;
import net.feedbacky.app.data.board.webhook.Webhook;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Plajer
 * <p>
 * Created at 26.10.2019
 */
@Getter
public class FetchWebhookDto implements FetchResponseDto<FetchWebhookDto, Webhook> {

  private long id;
  private String url;
  private String type;
  private List<String> triggers;

  @Override
  public FetchWebhookDto from(Webhook entity) {
    if(entity == null) {
      return null;
    }
    this.id = entity.getId();
    this.url = entity.getUrl();
    this.type = entity.getType().name();
    this.triggers = entity.getTriggers().stream().map(Enum::name).collect(Collectors.toList());
    return this;
  }

}
