<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>${windowTitle}</title>
    <script>
    	var PREFETCH_DISPATCH = ${prefetchDispatch};
    </script>
    <script type="text/javascript" src="/Alice/Alice.nocache.js"></script>
</head>
<body>
    <!-- OPTIONAL: include this if you want history support -->
    <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
        style="position: absolute; width: 0;height: 0; border: 0;"></iframe>

    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
       ${noScript}
    </noscript>
</body>
</html>