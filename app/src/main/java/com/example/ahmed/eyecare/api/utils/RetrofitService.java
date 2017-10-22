package com.example.ahmed.eyecare.api.utils;

import com.example.ahmed.eyecare.api.modelRequest.AddNotificationRequest;
import com.example.ahmed.eyecare.api.modelRequest.AddPhotoRequest;
import com.example.ahmed.eyecare.api.modelRequest.AddToMyAgenda;
import com.example.ahmed.eyecare.api.modelRequest.CommentRequest;
import com.example.ahmed.eyecare.api.modelRequest.LoginRequest;
import com.example.ahmed.eyecare.api.modelRequest.MessageDetailsRequest;
import com.example.ahmed.eyecare.api.modelRequest.MessageSendRequest;
import com.example.ahmed.eyecare.api.modelRequest.ParentRequest;
import com.example.ahmed.eyecare.api.modelRequest.PostRequest;
import com.example.ahmed.eyecare.api.modelRequest.SearchAttendeeRequest;
import com.example.ahmed.eyecare.api.modelRequest.UserInfoRequest;
import com.example.ahmed.eyecare.api.modelResponse.AboutResponse;
import com.example.ahmed.eyecare.api.modelResponse.AddPhotoResponse;
import com.example.ahmed.eyecare.api.modelResponse.AgendaListResponse;
import com.example.ahmed.eyecare.api.modelResponse.AnnouncementListResponse;
import com.example.ahmed.eyecare.api.modelResponse.AttendeeListResponse;
import com.example.ahmed.eyecare.api.modelResponse.ChatListResponse;
import com.example.ahmed.eyecare.api.modelResponse.CommentResponse;
import com.example.ahmed.eyecare.api.modelResponse.LoginResponse;
import com.example.ahmed.eyecare.api.modelResponse.MessageCountResponse;
import com.example.ahmed.eyecare.api.modelResponse.MessageDetailsResponse;
import com.example.ahmed.eyecare.api.modelResponse.NotificationListResponse;
import com.example.ahmed.eyecare.api.modelResponse.ParentResponse;
import com.example.ahmed.eyecare.api.modelResponse.PhotoListResponse;
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
    String LIST_NOTIFICATION = "list_of_user_notification.php";
    String ABOUT = "about_event.php";
    String PHOTO_LIST = "all_photos.php";
    String ADD_PHOTO = "add_photo.php";
    String ANNOUNCEMENT_LIST = "announcement_list.php";
    String ADD_NOTIFICATION = "send_notification.php";
    String LIKE_PHOTO = "add_photo_like.php";
    String COMMENT_PHOTO = "add_photo_comment.php";
    String COMMENT_POST = "add_post_comment.php";
    String UPDATE_USER_INFO ="update_data_after_login.php";

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


    @FormUrlEncoded
    @POST(LIST_NOTIFICATION)
    Call<NotificationListResponse> getListNotification(@Field(ParentRequest.USER_ID_KEY) int userId,
                                                       @Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(ABOUT)
    Call<AboutResponse> getAbout(@Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(PHOTO_LIST)
    Call<PhotoListResponse> getPhotoList(@Field(ParentRequest.USER_ID_KEY) int userId,
                                         @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(ADD_PHOTO)
    Call<AddPhotoResponse> addPhoto(@Field(ParentRequest.USER_ID_KEY) int userId,
                                    @Field(ParentRequest.EVENT_KEY) int event_id,
                                    @Field(AddPhotoRequest.IMAGE_PATH) String imagePathEncode);


    @FormUrlEncoded
    @POST(ANNOUNCEMENT_LIST)
    Call<AnnouncementListResponse> getAnnouncementList(@Field(ParentRequest.EVENT_KEY) int event_id);

    @FormUrlEncoded
    @POST(ADD_NOTIFICATION)
    Call<ParentResponse> addNotification(@Field(ParentRequest.EVENT_KEY) int event_id,
                                         @Field(AddNotificationRequest.TITLE) String title,
                                         @Field(AddNotificationRequest.BODY) String body,
                                         @Field(AddNotificationRequest.ADD_TO_DRAFT) int draft,
                                         @Field(AddNotificationRequest.TARGET) String target);


    @FormUrlEncoded
    @POST(LIKE_PHOTO)
    Call<ParentResponse> addLikeToPhoto(@Field(ParentRequest.USER_ID_KEY) int userId,
                                        @Field(AddPhotoRequest.PHOTO_ID) int photoId,
                                        @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(COMMENT_PHOTO)
    Call<CommentResponse> addCommentToPhoto(@Field(ParentRequest.USER_ID_KEY) int userId,
                                           @Field(CommentRequest.COMMENT) String comment,
                                           @Field(CommentRequest.PHOTO_ID) int photoId,
                                           @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(COMMENT_POST)
    Call<CommentResponse> addCommentToPost(@Field(ParentRequest.USER_ID_KEY) int userId,
                                           @Field(CommentRequest.COMMENT) String comment,
                                           @Field(CommentRequest.POST_ID) int postId,
                                           @Field(ParentRequest.EVENT_KEY) int event_id);


    @FormUrlEncoded
    @POST(UPDATE_USER_INFO)
    Call<ParentResponse> updateUserInfo(@Field(ParentRequest.USER_ID_KEY) int userId,
                                        @Field(ParentRequest.EVENT_KEY) int event_id,
                                        @Field(UserInfoRequest.COUNTRY_KEY) String country,
                                        @Field(UserInfoRequest.EMAIL_KEY) String email,
                                        @Field(UserInfoRequest.MOBILE_KEY) String mobile,
                                        @Field(UserInfoRequest.HOSPITAL_KEY) String hospital);
}
