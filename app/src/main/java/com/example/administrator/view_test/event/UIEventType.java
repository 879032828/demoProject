package com.example.administrator.view_test.event;

/**
 * 事件类型
 * Created by shen on 16/4/8.
 */
public enum UIEventType {

    /**
     * 清空聊天记录
     * messageType 消息类型，单聊或群聊
     * targetId  对应的id或groupId
     */
    ClearChatLog,
    /**
     * 清除某条首页最近消息
     * messageType 消息类型，单聊或群聊
     * targetId  对应的id或groupId
     */
    ClearChatRecent,
    /**
     * 收到发送消息回执
     * messageType 消息类型，单聊或群聊
     * targetId  对应的id或groupId
     * messageId 消息id
     */
    ReceivedSendMsgAck,
    /**
     * 收到发送消息失败回执
     * message 发送的消息 ReceivedMessageBodyBean
     * errmsgstr  错误消息
     */
    SendMsgFail,

    /**
     * 聊天会话列表，长按按消息后的事件
     * message ReceivedMessageBodyBean 对应的消息
     */
    UIChatMessageBubbleLongClick,
    /**
     * 聊天会话列表，长按头像后的事件
     * message ReceivedMessageBodyBean 对应的消息
     */
    UIChatAvatarLongClick,
    /**
     * 聊天会话列表，点击“发送失败的图标（消息旁边的红色的感叹号)”
     * message ReceivedMessageBodyBean 对应的消息
     */
    UIChatMessageFailIconClick,
    /**
     * websocket连接成功标志
     */
    WebSocketConnSuccess,

    /**
     * websocket开始连接
     */
    WebSocketStartConn,

    /**
     * websocket连接成功后主题为空，需要重新订阅
     */
    WebSocketConnSuccessReSub,
    /**
     * 收到电话/视频会议通知
     */
    ConferenceMsg,
    /**
     * 更新会话列表，最新一条消息
     * targetId string 目标类型
     * messageType string 消息类型
     * lastMsg  ReceivedMessageBodyBean 最新一条消息,可能为空，这时，最后一条消息为空
     */
    RefreshRecentChatLastMsg,
    /**
     * 网络状态改变事件
     * connected  boolean true->连接成功 false->连接失败
     */
    NetworkStatusChanged,
    /**
     * 从应用中心返回
     */
    ReturnFromH5Center,

    /**
     * 网盘创建文件夹成功事件
     */
    CreateFolderSuccess,

    /**
     * 网盘多选删除成功事件
     */
    MultiDeleteFileSuccess,

    /**
     * 网盘重命名成功事件
     */
    RenameFileSuccess,

    /**
     * 网盘移动文件或文件夹成功事件
     */
    PanMoveSuccess,
    /**
     * 获取未读的好友请求
     * friendlist 未读好友请求列表
     */
    GetUnReadFriendRequest,

    /**
     * 未读群组申请
     */
    GetUnReadGroupApply,

    /**
     * 对方接受我为好友消息
     */
    FriendMsgAccept,

    /**
     * 对方解除我好友消息
     */
    FriendMsgDelete,

    /**
     * 获取配置信息成功消息
     */
    GetConfigInfoSuccess,

    /**
     * 手机下线通知
     */
    OfflineMsg,
    /**
     * 更新用户数据成功
     */
    UpdateUserInfoMsg,
    /**
     * 上传文件进度通知
     */
    UploadProgressNotice,

    /**
     * 退群通知  包括被踢与群解析
     */
    GroupExitMsg,
    /**
     * 群成员变更
     */
    GroupMemChangeMsg,

    /**
     * 群改名通知
     */
    GroupCreatorChangeChangeMsg,
    /**
     * 群主成员转让
     */
    GroupNameChangeMsg,
    /**
     * 会议更新消息（取消/编辑）
     */
    MeetingUpdateMsg,
    /**
     * 更新团队群首页
     */
    UpdateTeamMainMsg,
    /**
     * 更新团队群资料页
     */
    UpdateTeaminfoMsg,
    /**
     * 更新会议列表
     */
    UpdateMeetListMsg,
    /**
     * 更新动态正文
     */
    UpdateDiscussDetailMsg,
    /**
     * 更新本地通讯录信息
     */
    UpdateLocalContactInfo,
    /**
     * 编辑备注
     */
    EditCollectRemark,
    /**
     * 刷新群组请求列表
     */
    RefreshGroupApplyList,
    /**
     * 公众号关注/取消关注通知
     */
    OfficialAccountsFollowStatusMsg,
    /**
     * 公众号改名通知
     */
    OfficialAccountsRenameMsg,
    /**
     * 更新最近联系人列表（消息首页与公众号动态页）
     */
    RefreshRecentMsg,
    /**
     * 我的显示小红点，同事圈有更新时
     */
    RefreshMicroBlog,
    /**
     * 隐藏我的小红点
     */
    DisMissMicroBlog,
    /**
     * 刷新Im列表
     */
    RefreshImList
}
