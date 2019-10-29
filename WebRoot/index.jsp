<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>足迹——首页</title>
	</head>
	<link rel="stylesheet" href="css/index.css" />
	<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
	<link rel="stylesheet" href="css/swiper.min.css" />
	<script type="text/javascript" src="js/swiper.min.js"></script>
	<link rel="stylesheet" href="css/btomlunbo.css" />
	<link rel="stylesheet" href="css/index_one.css" />
	<link rel="stylesheet" href="css/index_two.css" />
	<link rel="stylesheet" href="css/index_threr.css" />
	<link rel="stylesheet" href="css/index_four.css" />
	<!--<script type="text/javascript" src="js/mouserol.js" ></script>-->
	<script type="text/javascript" src="js/fxlunbo.js"></script>
	<link rel="stylesheet" href="css/index_frame.css" />
	<script type="text/javascript" src="js/index.js"></script>
	<script src="js/jquery-2.1.0.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/logname.js"></script>
	<script>
	function getVerifiCode() {
    $("#yzm_img").prop('src','servlet/GetVerificationServlet?a='+new Date().getTime());
	}
	</script>

	<body>
		<center>
			<!--
        	描述：标题头部
        -->
			<header class="header">
				<div class="mb"></div>
				<div class="header_nav">
					<div class="header_div">
						<a href="index.html"><img src="img/logo-01.png"></a>
						<ul class="ullist">
							<li class="addclass"><a href="index.jsp">首页</a></li>
							<li><a href="newslike.jsp">活动</a></li>
							<li><a href="dinzhiyou.jsp">定制游</a></li>
							<li><a href="html/lvyoumoshi.jsp">跟团游</a></li>
							<li><a href="autoyou.jsp">自助游</a></li>
							<li><a href="html/gonglue.jsp">攻略</a></li>
							<li><a href="html/dindan.jsp">我的订单</a></il>
							
						</ul>
						<span class="long">

						
							<%
							String name ="";
							if(session.getAttribute("name") != null){
								name =session.getAttribute("name").toString();
							}
							 
							%>
							<%=name %> 
							
							<a class="login">登录</a>|<a href="html/zhuce.jsp">注册</a>|&nbsp;<a href="html/personal.jsp">个人中心</a>

					</span>
					&nbsp;&nbsp;&nbsp;
					<span class="long">
					<a href="admin/index/adminlogin.jsp">管理员登录</a>
					</span>
					</div>
				</div>
			</header>
			<!--
        	标题头部结束
        -->
			<!--////////////登录////////////-->
			<!--登录最大的盒子-->
			<form action=""http://localhost:8080/zhuji/servlet/UserServlet?op=userlogin"" method="post">
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
								<input type="text" name = "user_login_phone" placeholder="您的邮箱/手机号" class="emailph" ><label class="la1"></label>
							</div>
							<div class="inputs">
								<input name = "user_login_password" type="password" placeholder="您的密码" class="pwd" ><label class="la2"></label>
							</div>
							<div class="yz">
								<input type="text" name="yanzheng"  placeholder="输入验证码" />
								<a href="javascript:getVerifiCode()">
								    <img id="yzm_img" style="cursor:pointer;width: 100px;height: 36px;margin: 5px 0 0 5px;border-radius: 3px;" title="点击刷新验证码" src="Mcake/getVerifiCode"/>
								</a>
							</div>
							<div class="wangji">
								<div><a href="#">忘记密码?</a></div>
								<div><input type="submit" value="登录"  class="loginbtn"></div>
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
			<div class="dw">
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<div class="swiper-slide imgbg">
							<a href="html/guiyang.jsp"><img src="img/banner1-01.jpg"></a>
						</div>
						<div class="swiper-slide imgbg">
							<a href="html/bijie.jsp"><img src="img/banner2-01.jpg"></a>
						</div>
						<div class="swiper-slide imgbg">
							<a href="html/zhuimeiqiandongnan.jsp"><img src="img/banner3.jpg"></a>
						</div>
					</div>
					<div class="swiper-pagination"></div>
				</div>
				<div class="input_div">
					<input class="search" placeholder="目的地">
					<button class="btn_sousuo">搜索</button>
				</div>
			</div>

			<div class="zw">
				<div class="img">
					<img src="img/23-01.jpg">
				</div>
				<div class="box">

					<div class="img1"><img src="img/2-01.png" alt="1" /></div>
					<div class="img2"><img src="img/2-f1.jpg" alt="2" />
						<div class="Lan_title">蓝海之迷清韵之美</div>
						<div class="Lan_pbox">
							<p>
								每个人心中都有一片海洋，安静而孤独。在这里，遇人城，遇心。有些人，从这里开始 浪，也有人，在这里找到故乡。除了文艺青年喜欢这座城市，资深驴友也同样爱着这里， 感受着幽邃的气息。
							</p>
							<p>
								THE EVERYONE HAS A HEART OF LIJIANG, QUIET AND LONELY.IN LIJIANG, IN THE CASE OF PEOPLECI TY, MEETS THE HERT. SOME PEOPLE, FROM HERE TO START WANDERING, THERERE PEOPLE HERE TO FIN DHOMETOWN。
							</p>
						</div>
						<span class="xq"><a href="html/guiyang.jsp">详情 ></a></span>
					</div>

				</div>
			</div>
			<!--<div style="background: red; width: 100px;height: 100px;"></div>-->
			<!--大盒子-->
			<div class="rm_box">
				<!--背景图片-->
				<div class="box_bj">
					<div class="bjdd"><img src="img/bjtp.png" width="100%" /></div>

					<!--九宫格图片-->
					<div class="box_jg">
						<ul class="box_jg_ul" id="box_jg_ul">
							<li>
								<a href="html/guiyang.jsp">
									<span class="lispic"><img src="img/pic_04.jpg">
								<div class="moban">
									<p class="title1">贵阳</p>
									<p class="jianjie">贵阳，简称“筑”，贵州省省会，贵州省的政治、经济、文化和金融中心。我国西南地区重要的中心城市之一</p>
								</div>
								</span>
								</a>

							</li>
							<li>
								<a href="html/zhuimeiqiandongnan.jsp">
									<span class="lispic"><img src="img2/qiandongnan7.jpg">
								<div class="moban">
									<p class="title1">黔东南</p>
									<p class="jianjie">黔东南苗族侗族自治州，位于贵州省东南部。下辖16个县市，州府凯里市</p>
								</div>
								</span>
								</a>

							</li>
							<li>
								<a href="html/bijie.jsp">
									<span class="lispic"><img src="img/pic_02.jpg">
								<div class="moban">
									<p class="title1">毕节</p>
									<p class="jianjie">毕节，川、滇、黔、渝结合部区域性中心城市，西南地区区域性重要综合交通枢纽，珠三角连接西南地区、长三角连接东盟地区的重要通道。”</p>
								</div>
								</span>
								</a>

							</li>
							<li>
								<a href="html/libo.jsp">
									<span class="lispic"><img src="img2/libo2.jpg">
								<div class="moban">
									<p class="title1">荔波</p>
									<p class="jianjie"> 荔波县，隶属黔南布依族苗族自治州，位于贵州省南部。</p>
								</div>
								</span>
								</a>

							</li>
							<li>
								<a href="html/qianxinan.jsp">
									<span class="lispic"><img src="img2/qianxinan1.png">
								<div class="moban">
									<p class="title1">黔西南</p>
									<p class="jianjie">兴义市，是贵州省黔西南布依族苗族自治州的地级行政区首府  地处贵州、云南、广西三省区结合部</p>
								</div>
								</span>
								</a>

							</li>
							<li>
								<a href="html/zuenyi.jsp">
									<span class="lispic"><img src="img2/zuenyi3.jpg">
								<div class="moban">
									<p class="title1">遵义</p>
									<p class="jianjie">遵义，简称“遵”，位于贵州省北部，黔川渝三省市结合部中心城市，是国家全域旅游示范区。</p>
								</div>
								</span>
								</a>

							</li>

						</ul>
					</div>
				</div>
			</div>

			<div class="boxx">
				<div class="tupian">
					<img src="img/11-03.jpg" width="100%" />
				</div>
				<div class="lanren">
					<ul>
						<li><img src="img/11-01.jpg" width="100%" /></li>
						<li></li>
						<li><img src="img/3-01.png" width="100%" />
							<a href="newslike.jsp">
								<div class="txt">
									<h3>更多活动</h3>
									<p>吉他、摄影等各类兴趣教程让你成为生活达人</p>
									<p>吉他、摄影等各类兴趣教程让你成为生活达人</p>
								</div>
							</a>
						</li>
						<li></li>
						<li><img src="img/tu2-01.jpg" width="100%" />
							<a href="dinzhiyou.jsp">
								<div class="txt">
									<h3>定制游玩</h3>
									<p>吉他、摄影等各类兴趣教程让你成为生活达人</p>
									<p>吉他、摄影等各类兴趣教程让你成为生活达人达人</p>
								</div>
							</a>
						</li>
						<li>
							<!--<a href="#"><div class="txt"><h3>亲子学堂</h3><p>教你如何和宝宝一起成长</p><p>吉他、摄影等各类兴趣教程让你成为生活达人</p></div></a>--></li>
						<li><img src="img/tu3-01.jpg" width="100%" />
							<a href="html/lvyoumoshi.jsp">
								<div class="txt">
									<h3>跟团游玩</h3>
									<p>吉他、摄影等各类兴趣教程让你成为生活达人</p>
									<p>吉他、摄影等各类兴趣教程让你成为生活达人</p>
								</div>
							</a>
						</li>
						<li>
							<!--<a href="#"><div class="txt"><h3>中小学/大学</h3><p>小学、初中、高中各科课程在线辅导</p><p>小学、初中、高中各科课程在线辅导</p></div></a>--></li>
						<li><img src="img/tu1-01.jpg" width="100%" />
							<a href="autoyou.jap">
								<div class="txt">
									<h3>自助游玩</h3>
									<p>吉他、摄影等各类兴趣教程让你成为生活达人</p>
									<p>吉他、摄影等各类兴趣教程让你成为生活达人</p>
								</div>
							</a>
						</li>
					</ul>
				</div>
				<div class="text">
					<h3>印度尼西亚·巴厘岛</h3>
					<p>BALIINDONESIA;</p>
					<p>BINGINDONESIA;INDONESIA;BINGI</p>
				</div>
				<div class="text1">
					<h3>希腊·爱琴海</h3>
					<p>HISTORY OF THE MALDIVES</p>
					<p>HISTOIRE DES MALDIVES;HISTORY OF MALDIVES</p>
				</div>
				<!--<div class="ziti">
				<p>THE MUTATION THE OF THE EARTH</p>
			</div>-->
			</div>

			<div class="imgbox">
				<img src="img/tu-01.jpg">
			</div>
			<div id="LoopDiv">
				<input id="S_Num" type="hidden" value="8" />
				<div id="starsIF" class="imageflow">
					<img src="img/1-01.jpg" alt="Picture" />
					<img src="img/1-02.jpg" alt="Picture" />
					<img src="img/1-03.jpg" alt="Picture" />
					<img src="img/1-04.jpg" alt="Picture" />
					<img src="img/1-05.jpg" alt="Picture" />
				</div>
			</div>
			<!--<iframe src="html/index_frame.html" class="iframe" scrolling="no"></iframe>-->
			<!--<iframe src="html/personal.html" class="iframe_two" scrolling="no" style="display: none;"></iframe>-->

			<!--
        	作者：1071341579@qq.com
        	时间：2017-01-16
        	描述：底部和返回头部按钮
        -->
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
		</center>
		<!--
        	作者：1071341579@qq.com
        	时间：2017-01-16
        	描述：整个页面结束
       -->
	</body>

	<script>
		//swiper图片轮播插件
		var swiper = new Swiper('.swiper-container', {
			pagination: '.swiper-pagination', //分页器
			paginationClickable: true, //分页器可点击却换轮播
			loop: true, //循环播放
			autoplay: 3000 //自动轮播间隔时间
		});
		var box_jg_ul = document.querySelector(".box_jg_ul")
		var abq = box_jg_ul.querySelectorAll("a")
		for (var i = 0; i < abq.length; i++) {
			(function(index) {
				abq[index].onclick = function() {
					//window.open("html/guiyang.jsp")
				}
			})(i)
		}
	</script>
	
</html>
