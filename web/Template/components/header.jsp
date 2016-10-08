<div class="topbar animated fadeInLeftBig"></div>

<!-- Header Starts -->
<div class="navbar-wrapper">
    <div class="container">

        <div class="navbar navbar-default navbar-fixed-top" role="navigation" id="top-nav">
            <div class="container">
                <div class="navbar-header">
                    <!-- Logo Starts -->
                    <a class="navbar-brand" href="#home"><img src="images/logo.png" alt="logo"></a>
                    <!-- #Logo Ends -->


                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                </div>


                <!-- Nav Starts -->
                <div class="navbar-collapse  collapse">
                    <ul class="nav navbar-nav navbar-right ">
                        <li class="active"><a href="#works">Home</a></li>
                        <li><a href="#modal-signin" class="signin" data-toggle="modal" data-target="#modal-signin">Entra</a></li>
                        <li><a href="ch_registrazione">Registrati</a></li>
                        <li><a href="#partners">Contatti</a></li>
                    </ul>
                </div>
                <!-- #Nav Ends -->

            </div>
        </div>

    </div>
</div>
<!-- begin:modal-signin -->
<div class="modal fade" id="modal-signin" tabindex="-1" role="dialog" aria-labelledby="modal-signin" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Sign in</h4>
            </div>
            <div class="modal-body">
                <form method="post" action="login" role="form">
                    <div class="form-group">
                        <label for="emailAddress">Email address</label>
                        <input type="email" name="email" class="form-control input-lg" placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" name="password" class="form-control input-lg" placeholder="Password">
                    </div>
                    <div class="modal-footer">
                        <p>Don't have account ? <a href="registrazione">Registrati qui.</a></p>
                        <input type="submit" class="btn btn-primary btn-block btn-lg" value="Sign in">
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<!-- end:modal-signin -->