function change() {
	$("#vCode").attr("src", "/blog/VerifyCodeServlet?" + new Date().getTime());
}