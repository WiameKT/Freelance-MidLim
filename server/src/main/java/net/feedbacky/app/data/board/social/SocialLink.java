package net.feedbacky.app.data.board.social;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.feedbacky.app.data.Fetchable;
import net.feedbacky.app.data.board.Board;
import net.feedbacky.app.data.board.dto.social.FetchSocialLinkDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * @author Plajer
 * <p>
 * Created at 23.12.2019
 */
@Entity
@Table(name = "boards_social_links")
@Getter
@Setter
@NoArgsConstructor
public class SocialLink implements Serializable, Fetchable<FetchSocialLinkDto> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "board_id")
  private Board board;
  private String logoUrl;
  private String url;

  @Override
  public FetchSocialLinkDto toDto() {
    return new FetchSocialLinkDto().from(this);
  }
}
