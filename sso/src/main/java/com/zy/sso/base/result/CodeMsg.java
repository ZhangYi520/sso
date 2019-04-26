package com.zy.sso.base.result;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * 
 **************************************************
 * @Description:通用后台CODE错误码
 * @author:CSX
 * @date:2019年01月14日
 **************************************************
 */
@Data
@Getter
@Setter
public class CodeMsg {
	
	private int code;
	private String msg;
	
	
	//通用的错误码 5001XX
	public static CodeMsg BASE_SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg BASE_SERVER_ERROR = new CodeMsg(500000, "服务端异常");
	public static CodeMsg BASE_BIND_ERROR = new CodeMsg(500001, "参数校验异常");
	public static CodeMsg BASE_LOGIN_ERROR = new CodeMsg(500002, "未登录");
	public static CodeMsg UN_AUTHORIZED = new  CodeMsg(500003, "用户授权认证未通过,客户端请求参数中token无效!");
	public static CodeMsg HAS_NO_PRIVILEGE = new  CodeMsg(500004, "用户无权限,请联系管理员!");
	public static CodeMsg NAME_IS_EXSITE = new CodeMsg(500005, "名称已存在");
	public static CodeMsg PARAMETER_ERROR = new CodeMsg(500006, "参数错误");
	public static CodeMsg KAPTCHA_ERROR = new CodeMsg(500007, "验证码错误");
	public static CodeMsg DATA_ERROR = new CodeMsg(500008, "数据为空");
	public static CodeMsg SQL_ERROR = new CodeMsg(500009, "SQL执行错误");
	public static CodeMsg KAPTCHA_NOSEND = new CodeMsg(500010, "验证码未发送，请发送验证码后再试！");
	//用户管理 501XXX
	public static CodeMsg ROLE_ID_IS_NOT_NULL = new  CodeMsg(501001, "角色ID不能为空");
	public static CodeMsg ADMIN_PASSWORD_EMPTY = new CodeMsg(501002, "用户名或密码不能为空！");
	public static CodeMsg ADMIN_PASSWORD_REEOR = new CodeMsg(501003, "用户名或密码错误！");
	public static CodeMsg ADMIN_USER_FREEZING = new CodeMsg(501004, "用户已锁定,当天密码输入错误次数超过3次,帐号已经禁止登录1分钟！");
	public static CodeMsg ADMIN_USER_IS_EXISTED = new CodeMsg(501005, "用户名已经存在！");
	public static CodeMsg SHIRO_LOGIN_COUNT = new CodeMsg(501006, "用户名已经存在！");
	public static CodeMsg PHONE_ERROR = new CodeMsg(501007, "手机号已经注册！");
	public static CodeMsg USERNAME_ERROR = new CodeMsg(501008, "用户不存在！");
	public static CodeMsg MEMBER_FULLDATA_ERROR = new CodeMsg(501009, "修改会员详细数据范围出错");
	public static CodeMsg PHONE_NOTEXIT = new CodeMsg(501010, "尚未注册的手机号！");
	public static CodeMsg ADMIN_EXIT = new CodeMsg(501011, "申请失败，该用户所在的机构已经存在管理员！");
	public static CodeMsg ADMIN_NOT = new CodeMsg(501012, "操作失败，该功能只有管理员能操作！");
	public static CodeMsg NOTE_SEND_ERROR = new CodeMsg(501013, "短信发送失败！");
	public static CodeMsg NOTE_DEL = new CodeMsg(501014, "市级经信委用户无法删除！");
	//文件上传502XXX
	public static CodeMsg FILE_ERROR = new CodeMsg(502001, "文件上传失败");
	public static CodeMsg FILE_TYPE_ERROR = new CodeMsg(502002, "文件类型错误");
	public static CodeMsg FILE_SIZE_ERROR = new CodeMsg(502003, "文件大小超出范围");
	public static CodeMsg FILE_NULL_ERROR = new CodeMsg(502004, "文件为空");
	
	//首页管理503XXX
	public static CodeMsg CAROUSEL_NAME_DUPLICATE = new CodeMsg(503001, "轮播图标题重复");
	public static CodeMsg WISDOM_TOPIC_DUPLICATE = new CodeMsg(503002, "文章标题重复");
	public static CodeMsg MSGSORT_NEME_DUPLICATE = new CodeMsg(503003, "最新消息分类名称重复");
	public static CodeMsg MSGCONTENT_NEME_DUPLICATE = new CodeMsg(503004, "最新消息内容文章名称重复");
	
	//分析师管理504XXX
	public static CodeMsg ANALYST_ESTATUS_ERROR = new CodeMsg(504001, "分析师未通过审核，不可修改启用状态");
	public static CodeMsg ANALYST_ESTATUS_ERROR2 = new CodeMsg(504002, "分析师审核已完成，不可修改审核状态");
	public static CodeMsg ANALYST_PERSONAL_PROFILE_ISNULL = new CodeMsg(504003, "分析师简介为空");
	public static CodeMsg ANALYST_PERSONAL_PROFILE_ERROR = new CodeMsg(504004, "分析师简介字数超出");
	public static CodeMsg ANALYST_LEVEL_ISNULL = new CodeMsg(504005, "分析师等级信息中，有空值");
	public static CodeMsg ANALYST_LEVEL_CHARGE_ERROR = new CodeMsg(504006, "分析师等级信息中，最小收费大于最大收费");
	public static CodeMsg ANALYST_LEVEL_CHARGE_ERROR2 = new CodeMsg(504007, "分析师等级信息中，收费标准不可和其他分析师等级类别完全相同");
	public static CodeMsg ANALYST_LEVEL_NAME_AGAIN = new CodeMsg(504008, "分析师等级信息中，名称不可重复");
	public static CodeMsg ANALYST_LEVEL_VALUE_AGAIN = new CodeMsg(504009, "分析师等级信息中，等级序号不可重复");
	public static CodeMsg ANALYST_LEADERBOARD_NAME_ISNULL = new CodeMsg(504010, "分析师排行榜名称不可为空");
	public static CodeMsg ANALYST_LEADERBOARD_NAME_AGAIN = new CodeMsg(504011, "分析师排行榜名称不可重复");
	public static CodeMsg WITHDRAW_CSTATUS_ERROR = new CodeMsg(504012, "提现审核已通过，不可再次修改");
	public static CodeMsg WITHDRAW_WSTATUS_ERROR = new CodeMsg(504013, "还未到提现进度，不可提现");
	public static CodeMsg WITHDRAW_REASON_ERROR = new CodeMsg(504014, "请填写理由后再提交");
	public static CodeMsg RECOMMENT_NAME_ISNULL = new CodeMsg(504015, "热门推荐分类名称不可为空");
	public static CodeMsg RECOMMENT_NAME_TOLONG = new CodeMsg(504016, "热门推荐分类名称过长");
	public static CodeMsg RECOMMENT_NAME_AGAIN = new CodeMsg(504017, "热门推荐分类名称不可重复");
	public static CodeMsg RECOMMENT_NOT_MODIFY = new CodeMsg(504018, "赛事玩法不可删除");
	public static CodeMsg ANALYST_PERSONAL_LEVELVALUE_ISNULL = new CodeMsg(504018, "分析师等级为空");

	//提问管理505XXX
	public static CodeMsg ASKTYPE_PARAMETER_ISNULL = new CodeMsg(505001, "问卷类型必填参数不完全");
	public static CodeMsg ASKTYPE_LIMITTIME_ISZERO = new CodeMsg(505002, "问卷类型限制回答时间不可为0");
	public static CodeMsg ASKTYPE_NAME_TOLONG = new CodeMsg(505003, "问卷类型提问名称长度不可大于64");
	public static CodeMsg ASKTYPE_MONEY_AGAIN = new CodeMsg(505004, "问卷类型打赏金额中有重复项");
	public static CodeMsg ASKTYPE_NAME_AGAIN = new CodeMsg(505005, "问卷类型提问名称在数据库中有重复");
	
	//首页管理505XXX
	public static CodeMsg MARKETSECTOR_NAME_DUPLICATE = new CodeMsg(505001, "市场特色板块名重复");
	
	//会员管理506XXX
	public static CodeMsg MSGTYPE_NAME_DUPLICATE = new CodeMsg(506001, "推送消息分类名称重复");
	public static CodeMsg MEMBERMSG_NAME_DUPLICATE = new CodeMsg(506002, "推送消息模板名称重复");
	public static CodeMsg PUSHMSG_ERROR_NOMEMBER = new CodeMsg(506003, "推送失败没有该会员");
	public static CodeMsg PUSHMSG_ERROR_NOMEMBERS = new CodeMsg(506004, "推送失败会员列表为空");
	
	//赛事管理507XXX
	public static CodeMsg SCHEDULE_REDIS_NEDATE = new CodeMsg(507001, "缓存没有数据，需更新缓存");
	public static CodeMsg LEAGUE_SORT_DUPLICATE = new CodeMsg(507002, "联赛分类重复");
	
	//是否收藏文章
	public static CodeMsg COLLECT_ARTICLE_Y = new CodeMsg(508001, "成功收藏");
	public static CodeMsg COLLECT_ARTICLE_N = new CodeMsg(508002, "取消收藏");
	public static CodeMsg COLLECT_STATE_ERROR = new CodeMsg(508003, "文章状态错误");
	public static CodeMsg ARTICLES_STATE_ERROR = new CodeMsg(508004, "图片为空");
	public static CodeMsg ARTICLES_IN_ERROR = new CodeMsg(508005, "操作流程为空");

	//融资服务509xxx
	public static CodeMsg FINACING_PAGEDATE_ISNULL = new CodeMsg(509001, "数据库没数据，服务器错误");
	
	//智能制造510xxx
	public static CodeMsg CREATE_PAGEDATE_ISNULL = new CodeMsg(510010, "数据库没数据，服务器错误");
	//咨询服务511xxx
	public static CodeMsg ADVISORY_QUERY_ERROR = new CodeMsg(511001, "查询失败，请输入正确条件！");
	
	public CodeMsg( int code,String msg ) {
		this.code = code;
		this.msg = msg;
	}	
	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}
	
}
