package utils;

/**
 * ClassName	: HttpResponseConstants
 * Function		: TODO
 * date 		: 2017年9月22日下午3:51:36
 * @author 		: taofeng
 * @version		: 1.0
 * @since   JDK 1.7
 */
public interface HttpResponseConstants {
	/**
	 * 公共
	 */
	public static class Public {

		/**0**/
		public static final Integer SUCCESS_CODE = 0;

		public static final Integer ERROR_CODE = 1;

		/**success**/
		public static final String POST_SUCCESS = "发布动态成功";

		public static final String COMMENT_SUCCESS = "评论成功";

		public static final String FRIENDS_SUCCESS = "关注好友成功";

		public static final String COLLECT_SUCCESS = "收藏成功";

		public static final String CANCEL_COLLECT_SUCCESS = "取消收藏成功";

		public static final String LIKE_SUCCESS = "喜欢成功";

		public static final String CANCEL_LIKE_SUCCESS = "取消喜欢成功";

		public static final String REGISTER_SUCCESS = "注册成功";

		public static final String LOGNAME_EXIST = "注册用户名已存在";

		public static final String USER_EDIT = "个人信息修改成功";

		/**error**/
		public static final String ERROR_700 = "参数错误";

	}
	
}
