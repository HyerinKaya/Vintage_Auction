package hello.hellospring.chat.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hello.hellospring.item.domain.Item;
import hello.hellospring.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="chatroom")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "itemId")
    private Item item;


    @ManyToOne
    @JoinColumn(name = "sellerNo")
    private Member sellerNo;

    @ManyToOne
    @JoinColumn(name = "buyerNo")
    private Member buyerNo;

    @OneToMany(mappedBy = "chatroom",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ChatMessage> chatMessageList = new ArrayList<>();

}