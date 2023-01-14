import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.*;

import org.junit.jupiter.api.*;

import java.rmi.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendshipsMongoTest {

    @InjectMocks
    static FriendshipsMongo friendshipsMongo;
    @Mock
    static FriendsCollection friends;

    @Test
    public void mockingWorksAsExpected(){
        Person joe = new Person("Joe");
        when(friends.findByName("Joe")).thenReturn(joe);
        assertEquals(joe, friends.findByName("Joe"));
    }

    @Test
    public void alexDoesNotHaveFriends(){
        assertEquals( 0, friendshipsMongo.getFriendsList("Alex").size());
    }

    @Test
    public void joeHas5Friends(){
        List<String> expected = List.of("Karol","Dawid","Maciej","Tomek","Adam");
        Person joe = mock(Person.class);
        when(friends.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(expected);
        assertThat(friendshipsMongo.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
    }

}