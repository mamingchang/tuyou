package com.example.administrator.card;

import java.io.Serializable;
import java.util.ArrayList;

public class DataOperation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String id = "";//�����ͷ���ID
	public static ArrayList<User> user = new ArrayList<User>();
	//public static ArrayList<UserBlack> userBlack=new ArrayList<UserBlack>();
	//public static ArrayList<UserFriends> userFriends = new ArrayList<UserFriends>();
	//public static ArrayList<UserHouse> userHouse = new ArrayList<UserHouse>();
	public static ArrayList<UserInformation> userInformation = new ArrayList<UserInformation>();
	public static ArrayList<Black>blacks = new ArrayList<Black>();
	public static ArrayList<Friends>friends = new ArrayList<Friends>();
	public static ArrayList<CommonHouse>commonHouses = new ArrayList<CommonHouse>();
	public static ArrayList<SelfHouse>selfHouses = new ArrayList<SelfHouse>();
	public static int tableNumber = -1;//�����ָ�����������ݱ�
	public static String[] table = {"","user","user_friends","user_black","user_house","user_information","friends","black","self_house","common_house"};
	public static int operationNumber = -1;//������ָ������������
	public static String[] operation = {"","登录验证","提交注册信息","修改个人信息","用户名查找好友","账号查找好友","获取黑名单","获取好友列表","删除好友","加入黑名单","移出黑名单","公有图库查询","私有图库查询","删除图片","上传图片"
	};
}
/*
 * "登录验证"OperationLogin
 *   将信息放置在User对象中；
 *   操作码是1
 *   表格码是1
 *   后记操作检查登录信息是否正确，正确就回应登录信息并为该用户创建代理线程，否则回应失败
 * "提交注册信息"OperationRegister
 *   将信息放置在User对象中
 *   操作码是2
 *   表格码是1
 *   后记根据提交的注册信息创建该用户，成功就回应新注册用户的信息并为该用户创建代理线程，否则回应失败
 *   （在创建用户时提前设置触发器创建该用户对应的friends表，black表，self_house表和common_house表，并在user_friends表，user_black表，user_house表和user_information表中添加相应条目）
 * "修改个人信息"OperationInformation
 *   将信息放置在UserInformation对象中
 *   操作码是3
 *   表格码是5
 *   后记根据提交的个人信息修改该用户定义user_information表，并将操作失败与否反馈给用户
 * "用户名查找好友"OperationFindFriendsByName
 *  将信息放置在User对象中
 *  操作码是4
 *  表格码是1
 *  后记根据获取的name字段来查询user表中所有使用该用户名的用户，失败返回空
 * "账号查找好友"OperationFindFriendsById
 *   将信息放置在User对象中
 *   操作码是5
 *   表格码是1
 *   后记根据获取的ID字段来查询user表中唯一使用该用户ID的用户，失败返回空
 * "获取黑名单"OperationBlack
 *   将信息放置在id属性值中
 *   操作码6
 *   表格码7
 *   使用id值来表明身份
 *   后记根据black+id来指明要查找的black表名并且将该表的表信息反馈给用户，失败返回空
 * "获取好友列表"OperationFriends
 *   将信息放置在id属性值中
 *   操作码7
 *   表格码6
 *   使用id值来表明身份
 *   后记根据friends+id来指明要查找的friends表名并且将该表的信息反馈给用户，失败返回空
 * "删除好友"OperationFriendsDelete
 *   将信息放置在id_new属性值中
 *   操作码8
 *   表格码6
 *   后记根据friends+id来指明要查找的friends表名并且将该表的指定好友删除并将新好友列表反馈给用户，失败返回空
 * "加入黑名单"OperationBlackIn
 *   将信息放置在id_new属性值中
 *   操作码是9
 *   表格码是7
 *   后记根据black+id来指明要查找的black表名并且将指定id的用户添加，并反馈该表的表信息反馈给用户，失败返回空
 *   （同时触发器实现若好友列表有这名好友删除，否则不操作）
 * "移出黑名单"OperationBlackOut
 *   将信息放置在id_new属性值中
 *   操作码是10
 *   表格码是7
 *   后记根据black+id来指明要查找的black表名并且将指定id的用户移出，并反馈该表的表信息反馈给用户，失败返回空
 * "公有图库查询"OperationCommonPictures
 * "私有图库查询"OperationSelfPictures
 * "删除图片"OperationPicturesDelete
 * "上传图片"OperationPicturesPull
 * */

	

