<!DOCTYPE HTML>
<html>
<head>
<meta content="text/html; charset=euc-kr">
<title>HTML5</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

$(function(){
    // tab 메뉴를 클릭하였을 때 동작함
    $(".tab ul li").click(function(){ 
         
        // 현재 선택되어있던 메뉴들을 초기화함
        $(".tab ul li").removeClass('on');
        $(".tab .conBox").removeClass('on');

        // 선택된 메뉴에 on 클래스를 주어 표기함
        $(this).addClass('on');

        // 선택된 탭의 data 값으로 content box를 선택함
        $("#"+$(this).data('id')).addClass('on');
    });
});
</script>

<style type="text/css">
.tab{
    width:200px;
    height:auto;
    overflow:hidden;
}
 
.tab ul{
    padding:0;
    margin:0;
    list-style:none;
    width:100%:
    height:auto;
    overflow:hidden;
}
 
.tab ul li{
    display:inline-block;
    width:33.3333%;
    float:left;
    line-height:40px;
    text-align:center;
    cursor:pointer;
}
 
.tab ul li:hover,
.tab ul li.on{
    background:#ccc;
}
 
.tab .conBox{
    width:100%;
    height:auto;
    overflow:hidden;
    min-height:200px;
    background:#ccc;
    display:none;
    text-align:center;
}
 
.tab .conBox.on{
    display:block;
}
</style>
</head>
<body>
	<div class="tab">
    <ul>
        <li class="on">tab #1</li>
        <li>tab #2</li>
        <li>tab #3</li>
    </ul>
    <div id="con1" class="conBox on">
        cont1
    </div>
    <div id="con2" class="conBox">
        cont2
    </div>
    <div id="con3" class="conBox">
        cont3
    </div>
</div>
</body>
</html>