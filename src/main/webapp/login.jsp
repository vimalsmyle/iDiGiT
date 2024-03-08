\<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="common/css/style1.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>IDigitronics</title>
</head>
<body class="login_form">
	<main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
	<div class="container">
		<div class="card login-card">
			<div class="row no-gutters">
				<div class="col-md-5">
					<img src="common/images/banner8.jpg" alt="banner8"
						class="login-card-img">
				</div>
				<div class="col-md-7">
					<div class="card-body">
						<div class="brand-wrapper">
							<img src="common/images/logo-white.png" alt="logo" class="logo">
						</div>
						<div id="testId">
							<p class="login-card-description">Sign into your account</p>
							<form id="test">
								<div class="form-group">
									<label for="email" class="sr-only">User Name</label> <input
										type="text" name="username" id="username" class="form-control"
										placeholder="User Name">
								</div>
								<div class="form-group mb-4 psd">
									<label for="password" class="sr-only">Password</label> <input
										type="password" name="password" id="password"
										class="form-control" placeholder="***********"> <i
										class="fa fa-eye-slash eyeToggle" aria-hidden="true"></i>
								</div>
								<input name="login" id="login"
									class="btn btn-block login-btn mb-4" type="button"
									value="Login">
							<span class="errorMessage"></span>
							</form>
						</div>

						<div id="loginTestId">
							<p class="login-card-description">Forget Password</p>
							<form id="test">
								<div class="form-group">
									<label for="email" class="sr-only">User Name</label> <input
										type="text" name="forgetusername" id="forgetusername" class="form-control"
										placeholder="User Name">
								</div>
								<input name="login" id="forgotButton"
									class="btn btn-block login-btn mb-4" type="button"
									value="Forget">
							</form>
						</div>

						<a href="#!" class="forgot-password-link" id="forgetClass"
							onClick="forget()">Forgot password?</a> <a href="#!"
							class="forgot-password-link" id="userClass" onClick="user()">sign
							in</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
		
		<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script src="js/login.js"></script>
</body>
<script>
	const togglePassword = document.querySelector('.eyeToggle');
	const password = document.querySelector('#password');

	togglePassword.addEventListener('click', function(e) {
		// toggle the type attribute
		const type = password.getAttribute('type') === 'password' ? 'text'
				: 'password';
		password.setAttribute('type', type);
		// toggle the eye / eye slash icon

		if (this.classList[1] == "fa-eye-slash") {
			this.className.replace("fa-eye", "fa-eye-slash");
		} else {
			this.className.replace("fa-eye-slash", "fa-eye");
		}

	});
	
	function forget(){
		$("#testId").hide();
		$("#userClass").show();
		$("#loginTestId").show();
		$("#forgetClass").hide();
		
	}
	function user(){
		$("#testId").show();
		$("#loginTestId").hide();
		$("#forgetClass").show();
		$("#userClass").hide();
	}
	
</script>

</html>