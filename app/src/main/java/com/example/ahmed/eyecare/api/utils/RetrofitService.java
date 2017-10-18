package com.example.ahmed.eyecare.api.utils;

import com.example.ahmed.eyecare.api.modelRequest.AddToMyAgenda;
import com.example.ahmed.eyecare.api.modelRequest.LoginRequest;
import com.example.ahmed.eyecare.api.modelRequest.MessageDetailsRequest;
import com.example.ahmed.eyecare.api.modelRequest.MessageSendRequest;
import com.example.ahmed.eyecare.api.modelRequest.ParentRequest;
import com.example.ahmed.eyecare.api.modelRequest.PostRequest;
import com.example.ahmed.eyecare.api.modelRequest.SearchAttendeeRequest;
import com.example.ahmed.eyecare.api.modelResponse.AgendaListResponse;
import com.example.ahmed.eyecare.api.modelResponse.AttendeeListResponse;
import com.example.ahmed.eyecare.api.modelResponse.ChatListResponse;
import com.example.ahmed.eyecare.api.modelResponse.LoginResponse;
import com.example.ahmed.eyecare.api.modelResponse.MessageCountResponse;
import com.example.ahmed.eyecare.api.modelResponse.MessageDetailsResponse;
import com.example.ahmed.eyecare.api.modelResponse.ParentResponse;
import com.example.ahmed.eyecare.api.modelResponse.PostListResponse;
import com.example.ahmed.eyecare.api.modelResponse.PostResponse;
import com.example.ahmed.eyecare.api.modelResponse.SpeakerListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ahmed on 10/10/17.
 */

public interface RetrofitService {
    String LOGIN = "login.php";
    String ALL_POST = "all_posts.php";
    String ADD_POST_CHECKIN = "add_post.php";
    String ALL_SPEAKER = "all_speaker.php";
    String ALL_ATTENDEE = "all_attends.php";
    String SEARCH_ATTENDEE = "search_attendees.php";
    String ALL_AGENDA = "agenda.php";
    String MESSAGE_DETAILS = "message_details.php";
    String SEND_MESSAGE = "send_message.php";
    String ADD_TO_MY_AGENDA = "add_to_my_agenda.php";
    String CHAT_LIST = "all_message.php";
    String MESSAGE_COUNT = "message_count.php";
    String LIKE_POST = "add_post_like.php";

    @FormUrlEncoded
    @POST(LOGIN)
    Call<LoginResponse> login(@Field(LoginRequest.EMAIL_KEY) String email,
                              @Field(LoginRequest.CODE_KEY) String code,
                              @Field(LoginRequest.TOKEN_KEY) String token,
                              @Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(ALL_POST)
    Call<PostListResponse> getAllPost(@Field(ParentRequest.USER_ID_KEY) int userId,
                                      @Field(ParentRequest.PAGE_NUMBER_KEY) int pageNumber,
                                      @Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(ADD_POST_CHECKIN)
    Call<PostResponse> addPostOrCheckIn(@Field(ParentRequest.USER_ID_KEY) int userId,
                                        @Field(PostRequest.POST_KEY) String post,
                                        @Field(PostRequest.CHECK_IN_KEY) int checkIn,
                                        @Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(ALL_SPEAKER)
    Call<SpeakerListResponse> getAllSpeaker(@Field(ParentRequest.USER_ID_KEY) int userId,
                                            @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(ALL_ATTENDEE)
    Call<AttendeeListResponse> getAllAttendee(@Field(ParentRequest.PAGE_NUMBER_KEY) int pageNumber,
                                              @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(SEARCH_ATTENDEE)
    Call<AttendeeListResponse> searchAttendee(@Field(ParentRequest.PAGE_NUMBER_KEY) int pageNumber,
                                              @Field(ParentRequest.EVENT_KEY) int event_id,
                                              @Field(SearchAttendeeRequest.SEARCH_STRING) String searchWord);


    @FormUrlEncoded
    @POST(ALL_AGENDA)
    Call<AgendaListResponse> getAllAgenda(@Field(ParentRequest.USER_ID_KEY) int userId,
                                          @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(MESSAGE_DETAILS)
    Call<MessageDetailsResponse> getMessageDetails(@Field(MessageDetailsRequest.CURRENT_USER) int userId,
                                                   @Field(MessageDetailsRequest.OTHER_USER) int otherUserId,
                                                   @Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(SEND_MESSAGE)
    Call<ParentResponse> sendMessage(@Field(MessageSendRequest.SENDER_ID) int userId,
                                     @Field(MessageSendRequest.RECEIVER_ID) int otherUserId,
                                     @Field(MessageSendRequest.MESSAGE) String message,
                                     @Field(MessageSendRequest.IMAGE) String image,
                                     @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(ADD_TO_MY_AGENDA)
    Call<ParentResponse> addToMyAgenda(@Field(AddToMyAgenda.SESSION_ID) int sessionId,
                                       @Field(ParentRequest.USER_ID_KEY) int userId,
                                       @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(CHAT_LIST)
    Call<ChatListResponse> getChatList(@Field(ParentRequest.USER_ID_KEY) int userId,
                                       @Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(MESSAGE_COUNT)
    Call<MessageCountResponse> getMessageCount(@Field(ParentRequest.USER_ID_KEY) int userId,
                                               @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(LIKE_POST)
    Call<ParentResponse> addLikeToPost(@Field(ParentRequest.USER_ID_KEY) int userId,
                                         @Field(PostRequest.POST_ID_KEY) int postId,
                                         @Field(ParentRequest.EVENT_KEY) int event_id);

}
