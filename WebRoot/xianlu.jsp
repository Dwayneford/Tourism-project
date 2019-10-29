<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>线路介绍</title>
		<link rel="stylesheet" href="css/lx.css" />
		<link rel="stylesheet" href="css/flx.css" />
		<link rel="stylesheet" href="css/heaerpublic.css" />
		<link rel="stylesheet" href="css/index.css" />
		<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
		<style>
			/*立即定付*/
			
			
		</style>
		<script src="js/jquery-2.1.0.js"></script>
	<script>
	function getVerifiCode() {
    $("#yzm_img").prop('src','servlet/GetVerificationServlet?a='+new Date().getTime());
	}
	</script>
	</head>

	<body>
		<header class="header">
			<div class="mb"></div>
			<div class="header_nav">
				<div class="header_div">
					<a href="index.jsp"><img src="img/logo-01.png"></a>
					<ul class="ullist">
						<li class="addclass"><a href="index.jsp">首页</a></li>
						<li><a href="newslike.jsp">活动</a></li>
						<li><a href="dinzhiyou.jsp">定制游</a></li>
						<li><a href="html/lvyoumoshi.jsp">跟团游</a></li>
						<li><a href="autoyou.jsp">自助游</a></li>
						<li><a href="html/gonglue.jsp">攻略</a></li>
					</ul>
					<span class="long">
						<a class="login">登录</a>|<a href="html/zhuce.jsp">注册</a>|&nbsp<a href="html/personal.jsp">个人中心</a>
					</span>
				</div>
			</div>
		</header>
		<!--
        	标题头部结束
        -->
		<!--////////////登录////////////-->
		<!--登录最大的盒子-->
		<form action="http://localhost:8089/lvyou/servlet/UserLoginServlet" method="post">
			<div class="login_box">
				<!--为了控制位子-->
				<div class="ding">
					<!--登录左边版块-->
					<div class="login_L">
						<h3>登录足迹</h3>
						<div class="text_tj">
							<p>如果你不出去走走，你就会以为这就是世界。</p>
							<p>——《天堂电影院》</p>
						</div>
						<div class="inputs">
							<input type="text" name="user_phinto" placeholder="您的邮箱/手机号" class="emailph"><label class="la1"></label>
						</div>
						<div class="inputs">
							<input type="password" name="user_password" placeholder="您的密码" class="pwd"><label class="la2"></label>
						</div>
						<div class="yz">
								<input type="text" name="yanzheng" id="" value="" placeholder="输入验证码" />
								<a href="javascript:getVerifiCode()">
								    <img id="yzm_img" style="cursor:pointer;width: 100px;height: 36px;margin: 5px 0 0 5px;border-radius: 3px;" title="点击刷新验证码" src="Mcake/getVerifiCode"/>
								</a>
							</div>
						<div class="wangji">
							<div><a href="#">忘记密码?</a></div>
							<div><input type="submit" value="登录" class="loginbtn"></div>
						</div>
					</div>
					<!--登录右边版块-->
					<div class="login_R">
						<img src="img2/timg (7).jpg" height="480px" width="327px">
					</div>
					<!--退出-->
					<div class="tuichu">
						×
					</div>
				</div>
			</div>
		</form>
		<!--/////////////// 登录页面结束/////////////-->
		<!--
        	作者：1071341579@qq.com
        	时间：2017-01-16
        	描述：引用子页面
        -->
		<div class="imgs">
			<img src="img/log-2.jpg" width="100%">
		</div>
		<!--搜索-->
		<div class="ms_inputs">
			<div class="ms_dangqian">
				<p>
					<input type="text" value="马尔代夫" class="ms_adress">
					<label class="ms_dq">当前站</label>
				</p>

			</div>
			<select class="xiala">
					<option selected="selected">路线</option>
					<option>路线一</option>
					<option>路线二</option>
					<option>路线三</option>
				</select>
			<input type="text" class="ms_search" />
			<input type="button" class="ms_btn" value="搜索" />

		</div>

		<!--跟团游-->
		<div class="gengtuan">
			<p>线路介绍</p>
		</div>
		<!--行程安排-->
		<div class="ts">
			<span class="ts_one">行程安排</span>
			<span class="md">Maldives</span>
		</div>
		<!--行程内容-->
		<div class="xianluBox">
			<div class="xlLeft">
				<ul class="ulfirst">
					<li class="active">第一天</li>
					<li>第二天</li>
					<li>第三天</li>
					<li>第四天</li>
					<li>第五天</li>
					<li>第六天</li>
				</ul>
			</div>
			<div class="xlcontent">
				<ul class="lastul">
					<li>
						<div class="right_text">
							<div class="text_img">
								<img src="img/shu.jpg" />
								<div class="xl"></div>
							</div>
							<div class="text_right">
								<div class="j_left">
									<div class="j_nr">
										<span class="jd_l">
											景点
										</span>
										<span class="xms">
											
										</span>
										<span class="long1">
											叶天古城
										</span>
									</div>
									<p class="ds">9:30-12:00</p>
									<div class="longya">
										<p>亚龙湾是一个半月形的海湾，绵延7公里，平缓而宽阔。这里的沙粒洁白细软，蔚蓝的海水清澈晶莹，能见度达 9米。 五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游中，跃入海水中畅游，五彩缤纷的鱼儿似乎触手可及。这里终年
										</p>
									</div>
										
								</div>
								<div class="j_ringht">
									<img src="img/1-04.jpg" width="100%"/>		
								</div>
								<div class="lll">
									
								</div>
								
							</div>
						</div>
					</li>
					
					
					<li>
						<div class="right_text">
							<div class="text_img">
								<img src="img/shu.jpg" />
								<div class="xl"></div>
							</div>
							<div class="text_right">
								<div class="j_left">
									<div class="j_nr">
										<span class="jd_l">
											景点
										</span>
										<span class="xms">
											
										</span>
										<span class="long1">
											亚龙湾
										</span>
									</div>
									<p class="ds">9:30-12:00</p>
									<div class="longya">
										<p>亚龙湾是一个半月形的海湾，绵延7公里，平缓而宽阔。这里的沙粒洁白细软，蔚蓝的海水清澈晶莹，能见度达 9米。 五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游中，跃入海水中畅游，五彩缤纷的鱼儿似乎触手可及。这里终年
										</p>
									</div>
										
								</div>
								<div class="j_ringht">
									<img src="img/1-02.jpg" width="100%"/>		
								</div>
								<div class="lll">
									22222
								</div>
								
							</div>
						</div>
					</li>
					
					
					<li>
						<div class="right_text">
							<div class="text_img">
								<img src="img/shu.jpg" />
								<div class="xl"></div>
							</div>
							<div class="text_right">
								<div class="j_left">
									<div class="j_nr">
										<span class="jd_l">
											景点
										</span>
										<span class="xms">
											
										</span>
										<span class="long1">
											槟榔谷
										</span>
									</div>
									<p class="ds">9:30-12:00</p>
									<div class="longya">
										<p>亚龙湾是一个半月形的海湾，绵延7公里，平缓而宽阔。这里的沙粒洁白细软，蔚蓝的海水清澈晶莹，能见度达 9米。 五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游中，跃入海水中畅游，五彩缤纷的鱼儿似乎触手可及。这里终年
										</p>
									</div>
										
								</div>
								<div class="j_ringht">
									<img src="img/1-02.jpg" width="100%"/>		
								</div>
								<div class="lll">
									
								</div>
								
							</div>
						</div>
					</li>
					
					
					<li>
						<div class="right_text">
							<div class="text_img">
								<img src="img/shu.jpg" />
								<div class="xl"></div>
							</div>
							<div class="text_right">
								<div class="j_left">
									<div class="j_nr">
										<span class="jd_l">
											景点
										</span>
										<span class="xms">
											
										</span>
										<span class="long1">
											三亚宋城文化旅游度假区
										</span>
									</div>
									<p class="ds">9:30-12:00</p>
									<div class="longya">
										<p>亚龙湾是一个半月形的海湾，绵延7公里，平缓而宽阔。这里的沙粒洁白细软，蔚蓝的海水清澈晶莹，能见度达 9米。 五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游中，跃入海水中畅游，五彩缤纷的鱼儿似乎触手可及。这里终年
										</p>
									</div>
										
								</div>
								<div class="j_ringht">
									<img src="img/1-03.jpg" width="100%"/>		
								</div>
								<div class="lll">
									
								</div>
								
							</div>
						</div>
					</li>
					
					
					<li>
						<div class="right_text">
							<div class="text_img">
								<img src="img/shu.jpg" />
								<div class="xl"></div>
							</div>
							<div class="text_right">
								<div class="j_left">
									<div class="j_nr">
										<span class="jd_l">
											景点
										</span>
										<span class="xms">
											
										</span>
										<span class="long1">
											天涯海角
										</span>
									</div>
									<p class="ds">9:30-12:00</p>
									<div class="longya">
										<p>亚龙湾是一个半月形的海湾，绵延7公里，平缓而宽阔。这里的沙粒洁白细软，蔚蓝的海水清澈晶莹，能见度达 9米。 五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游中，跃入海水中畅游，五彩缤纷的鱼儿似乎触手可及。这里终年
										</p>
									</div>
										
								</div>
								<div class="j_ringht">
									<img src="img/1-04.jpg" width="100%"/>		
								</div>
								<div class="lll">
									
								</div>
								
							</div>
						</div>
					</li>
					
					
					<li>
						<div class="right_text">
							<div class="text_img">
								<img src="img/shu.jpg" />
								<div class="xl"></div>
							</div>
							<div class="text_right">
								<div class="j_left">
									<div class="j_nr">
										<span class="jd_l">
											景点
										</span>
										<span class="xms">
											
										</span>
										<span class="long1">
											情人岛
										</span>
									</div>
									<p class="ds">9:30-12:00</p>
									<div class="longya">
										<p>亚龙湾是一个半月形的海湾，绵延7公里，平缓而宽阔。这里的沙粒洁白细软，蔚蓝的海水清澈晶莹，能见度达 9米。 五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游五颜六色的珊瑚礁和各种热带鱼儿穿梭其中，跃入海水中畅游中，跃入海水中畅游，五彩缤纷的鱼儿似乎触手可及。这里终年
										</p>
									</div>
										
								</div>
								<div class="j_ringht">
									<img src="img/1-05.jpg" width="100%"/>		
								</div>
								<div class="lll">
									
								</div>
								
							</div>
						</div>
					</li>
				</ul>

			</div>

		</div>
		<!--立即定付结束-->
		<div class="dtop">
			<div class="fhtext">返回顶部</div>
			<div class="fhimg"><img src="icon/XUP.jpg"></div>
			<div class="lianxi"><img src="icon/LXKH.jpg"></div>
		</div>
		<footer class="footer">
			<ul>
				<li style="color: #000000;">京ICP备11017824号-4 / 京ICP证130164号</li>
				<li style="color: #000000;">北京市公安局朝阳分局备案编号：110105000501</li>
				<li style="color: #000000;">Copyright @ 2006-2017 ZCOOL</li>
			</ul>
			<ul>
				<li style="color: #000000;">京ICP备11017824号-4 / 京ICP证130164号 版权信息</li>
				<li style="color: #000000;">京ICP备11017824号-4 / 京ICP证130164号</li>
				<li style="color: #000000;">北京市公安局朝阳分局备案编号：110105000501</li>
				<li style="color: #000000;">Copyright @ 2006-2017 ZCOOL</li>
			</ul>
		</footer>
		
		<script>
			$(function(){
				
				//定义当前位置
				var page = 0;
				//得到总页数
				var contpage = $(".ulfirst li").length-1;
				$(".ulfirst li").click(function(){
					//得到当前点击的索引
					var index = $(this).index();
					//显示隐藏lastul中的li
					$(".lastul li").eq(index).show().siblings("li").hide();
					//控制ulfirst中li点击后的背景
					$(this).addClass("active").siblings().removeClass("active");
				})
				
				//鼠标移上box清除定时器
				$(".box").hover(function(){
					clearInterval(data);
				},function(){
					
						if(page == contpage){
							page = 0;
						}else{
							page++;
						}
						//调用ulfirst中li的点击事件
						$(".ulfirst li").eq(page).click();
				
				})
			})
			
			
//			$(document).ready(function(){
//				$(".xlcontent .lastul li .right_text1").click(function(){
//					alert(2)
//					$(".right_text1").siblings().css("display","block");
//					
//				})
//				
//				$("li:nth-of-type(2)").click(function(){
//					alert(2);
//				})
//				
//			});
			
		</script>
		
	</body>

</html>
