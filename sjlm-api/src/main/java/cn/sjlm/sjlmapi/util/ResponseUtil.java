package cn.sjlm.sjlmapi.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 响应操作结果
 * <pre>
 *  {
 *      code： 状态码，
 *      msg：消息,
 *      data：  响应数据
 *  }
 * </pre>
 *
 * <p>
 * 状态码：
 * <ul>
 * <li> 200，成功；
 * <li> 4xx，前端相关错误，说明前端开发者需要重新了解后端接口使用规范：
 * <ul>
 * <li> 401，参数缺失；
 * <li> 402，参数值错误，即前端传递的参数值不符合后端接收范围。
 * <li> 403，无效的参数。
 * <li> 404，请求参数组合无效。
 * <li> 405，缺失Token。
 * <li> 406，Token无效。
 * <li> 407，Appkey缺失。
 * <li> 408，Appkey无效。
 * <li> 409，Token缺失。
 * <li> 410，Token无效。
 * </ul>
 * <li> 5xx，后端错误
 * <ul>
 * <li> 500，未知错误；
 * <li> 501，验证失败，即后端要求用户登录；
 * <li> 502，系统内部错误，即没有合适命名的后端内部错误；
 * <li> 503，业务不支持，即后端虽然定义了接口，但是还没有实现功能；
 * <li> 504，更新数据失效，即后端采用了乐观锁更新，而并发更新时存在数据更新失效；
 * <li> 505，更新数据失败，即后端数据库更新失败（正常情况应该更新成功）。
 * <li> 506，无操作权限。
 * <li> 507，请求超时。
 * <li> 508，无当前API访问权限。
 * <li> 509，当日API访问量已达到上限。
 * <li> 510，API地址不存在。
 * <li> 511，请求IP无效。
 * <li> 512，请求方法错误。
 * <li> 513，禁止访问。
 * <li> 514，访问过于频繁。
 * <li> 515，请求Header无效。
 * <li> 516，请求包含非UTF-8编码字符。
 * <li> 517，请求参数值超过上限
 * <li> 518，数据存在重复
 * <li> 530，短信验证码错误。
 * <li> 531，短信验证码过期 + (短信验证码手机号不存在 @tmp) 
 * <li> 532，短信验证码手机号不存在
 * <li> 570 不是VIP。
 * <li> 571，VIP过期
 * <li> 58x，账户相关报错
 * <li>		580, 账户余额不足
 * </ul>
 */
public class ResponseUtil {
	private static final String CODE = "code";
	private static final String MSG = "msg";
	private static final String DATA = "data";
	
	private static final int SUCCESS = 200;
	private static final int UNKINW_ERROR = 500;
	
    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put(CODE, SUCCESS);
        obj.put(MSG, "成功");
        
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put(CODE, SUCCESS);
        obj.put(MSG, "成功");
        obj.put(DATA, data);
        
        return obj;
    }

    public static Object ok(String msg, Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put(CODE, SUCCESS);
        obj.put(MSG, msg);
        obj.put(DATA, data);
        
        return obj;
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put(CODE, UNKINW_ERROR);
        obj.put(MSG, "未知错误");
        
        return obj;
    }
    
    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put(CODE, errno);
        obj.put(MSG, errmsg);
        
        return obj;
    }
    
    public static Object fail(int errno, Object object) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put(CODE, errno);
        obj.put(MSG, object);
        
        return obj;
    }
    
    public static Object fail(int errno, String msg, Object object) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put(CODE, errno);
        obj.put(MSG, msg);
        obj.put(DATA, object);
        
        return obj;
    }
    
    /*
     * 老的代码，暂时保留
     */
    public static Object badArgument(){
        return fail(401, "参数不对");
    }
    public static Object badArgumentValue(){
        return fail(402, "参数值不对");
    }
    
    public static Object missingParameter(){
        return missingParameter(null);
    }
    
    public static Object missingParameter(String msg){
        return fail(401, StringUtils.isNoneEmpty(msg)? msg :"Required parameter is missing. (缺少必选请求参数)");
    }
    
    public static Object invalidParameterValue(){
        return invalidParameterValue(null);
    }
    
    public static Object invalidParameterValue(String msg){
        return fail(402, StringUtils.isNoneEmpty(msg)? msg : "Parameter value is invalid. (请求参数值无效)");
    }
    
    public static Object invalidParameter(){
        return invalidParameter(null);
    }
    
    public static Object invalidParameter(String msg){
        return fail(403, StringUtils.isNoneEmpty(msg)? msg : "Parameter is invalid. (请求参数无效)");
    }
    
    public static Object invalidParameterSet(){
        return invalidParameterSet(null);
    }
    
    public static Object invalidParameterSet(String msg){
        return fail(404, StringUtils.isNoneEmpty(msg)? msg : "Parameters set is invalid. (请求参数组合无效)");
    }
    
    public static Object missingAppkey(){
        return fail(405, "Appkey is missing. (无appkey参数)");
    }

    public static Object invalidAppkey(){
        return fail(406, "Invalid appkey. (appkey参数值无效)");
    }
    
    public static Object missingSign(){
        return fail(407, "Sign is missing. (无sign签名参数)");
    }

    public static Object invalidSign(){
        return fail(408, "Invalid sign. (sign签名参数值无效)");
    }

    public static Object missingToken(){
        return fail(409, "Token is missing. (无令牌Token)");
    }

    public static Object invalidToken(){
        return fail(410, "Invalid Token. (令牌Token值无效)");
    }
    
    public static Object noUser() {
        return fail(500, "没有这个用户");
    }
    
    public static Object unlogin() {
        return fail(501, "请登录");
    }

    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    public static Object unsupport() {
        return fail(503, "业务不支持");
    }

    public static Object updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }

    public static Object unauthz() {
        return fail(506, "无操作权限");
    }
    
    public static Object timeout(){
        return fail(507, "Request time out. (请求超时)");
    }

    public static Object noAccessible(){
        return fail(508, "This API is not accessible. (无当前API访问权限)");
    }

    public static Object reachDailyLimit(){
        return fail(509, "You have reached the daily limit. (当日API访问量已达到上限)");
    }
    
    public static Object apiNotExists(){
        return fail(510, "API does not exist. (API地址不存在)");
    }
    
    public static Object invalidIP(){
        return fail(511, "IP is invalid. (请求IP无效)");
    }
    
    public static Object errorMethod(){
        return fail(512, "Error method. (请求方法错误)");
    }
    
    public static Object accessForbidden(){
        return fail(513, "Access forbidden. (禁止访问)");
    }
    
    public static Object tooFrequency(){
        return fail(514, "You have exceeded the allowed frequency. (访问过于频繁)");
    }
    
    public static Object invalidRequestHeader(){
        return fail(515, "Invalid request-header: {0}. (HTTP Header {0} 无效)");
    }
    
    public static Object invalidCharacters(){
        return fail(516, "Request contains invalid UTF-8 characters. (HTTP请求包含非UTF-8编码字符: {0})");
    }
    
    public static Object parameterContainsMore(){
        return fail(517, "Parameter contains more than {1} items: {0}. (请求参数值数量超过{1}上限: {0})");
    }
    
    public static Object duplicated(String source){
        return fail(518, String.format("Data is duplicated: {%s} (数据存在重复)", source));
    }
    
    public static Object duplicated(){
        return fail(518, "Data is duplicated: (数据存在重复)");
    }
    
    public static Object notExists(String source){
        return fail(519, String.format("Data is not exists: {%s} (数据不存在)", source));
    }
    
    public static Object notExists(){
        return fail(519, "Data is not exists: (数据不存在)");
    }
    
    /*
     * 超过数据余额现在, 比如商品数量. 账户余额(580)单独控制
     */
    public static Object overNumBalance(String source){
        return fail(520, String.format("Over data balance limit: {%s} (超出数量限制)", source));
    }
    
    /*
     * 超过数据余额现在, 比如商品数量. 账户余额(580)单独控制
     */
    public static Object overNumBalance(){
        return fail(520, "Over data balance limit: (超出数量限制)");
    }
    
    public static Object smscodeError(){
        return fail(530, "短信验证码错误");
    }
    
    public static Object smsCodeExpired(){
        return fail(531, "短信验证码过期或者手机号不存在");
    }
    
    public static Object smsCodeMobileNotExists(){
        return fail(532, "短信验证码手机号不存在");
    }
    
    public static Object notVip(){
        return fail(570, "not VIP: (不是会员)");
    }
        
    public static Object vipExpiresed(Object data){
        return fail(571, "vip account is expiresed: (会员过期)");
    }
    
    public static Object accountNotEnough(Object object){
        return fail(580, "account balance is not enough: (账户余额不足)", object);
    }
    
    public static Object adminNotFind() {
    	return fail(581, "管理员不存在");
    }
    
    public static Object idsNull() {
    	return fail(590, "删除id为空");
    }
 
}

