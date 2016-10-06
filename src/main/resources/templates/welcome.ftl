<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HTML Email</title>
</head>
<body>
    
    <div class="email-background" style="background-color: #eee;height: 800px;padding: 10px;">
        <div class="pre-header" style="background-color: #eee;color: #eee;font-size: 10px;">Welcome to the Dev Center.</div>
        <div class="logo" style="background-image: url('http://stgconsulting.com/wp-content/uploads/2014/02/stg-logo.png'); height: 60px;width: 180px;padding-bottom: 20px;background-repeat: no-repeat;margin: 0 auto;"></div>
        <div class="email-container" style="max-width: 500px;background-color: white;margin: 0 auto;font-family: arial;border-radius: 10px;padding: 40px;padding-top: 20px;margin-bottom: 50px;">
        
            <h1 style="color: #18ab8f;">Hello ${name}!</h1>
            <p style="font-size: 20px;line-height: 28px;">Welcome to the Dev Center, we are so excited for you to <strong>Learn and Grow your skills</strong> with us. To get started, we invited you to our super-fantastic Onboarding App. Click on the button below to get started.</p>
        
            <div class="button" style="text-align: center;margin: 30px;font-size: 25px;">
                <a href="#" style="text-decoration: none;display: inline-block;background-color: #18ab8f;color: white;padding: 12px 90px;border-radius: 7px;">START</a>
            </div>
            
            <p style="font-size: 20px;line-height: 28px;">See you soon!</p>
            <p style="font-size: 20px;line-height: 28px;">Cheers, <br>The team at STG Consulting</p>
        </div>
    </div>

</body>
</html>