<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>SwiftFood - Food Delivered Fast</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .floating-emojis {
            position: absolute;
            top: 0; left: 0;
            width: 100%; height: 100%;
            pointer-events: none;
            z-index: 0;
        }
        .emoji {
            position: absolute;
            animation: float 6s ease-in-out infinite;
            opacity: 0.5;
        }
        .e1 { left:5%;  top:10%; font-size:50px; animation-delay:0s;   }
        .e2 { left:15%; top:70%; font-size:35px; animation-delay:1s;   }
        .e3 { left:80%; top:15%; font-size:45px; animation-delay:2s;   }
        .e4 { left:70%; top:75%; font-size:40px; animation-delay:0.5s; }
        .e5 { left:45%; top:5%;  font-size:30px; animation-delay:1.5s; }
        .e6 { left:90%; top:50%; font-size:38px; animation-delay:2.5s; }
        .e7 { left:25%; top:85%; font-size:32px; animation-delay:3s;   }
        .e8 { left:60%; top:40%; font-size:42px; animation-delay:3.5s; }
        @keyframes float {
            0%   { transform: translateY(0px) rotate(0deg);   }
            50%  { transform: translateY(-20px) rotate(10deg); }
            100% { transform: translateY(0px) rotate(0deg);   }
        }
    </style>
</head>
<body class="landing">
<div class="floating-emojis">
    <span class="emoji e1">🍕</span>
    <span class="emoji e2">🍔</span>
    <span class="emoji e3">🌮</span>
    <span class="emoji e4">🍜</span>
    <span class="emoji e5">🍣</span>
    <span class="emoji e6">🧁</span>
    <span class="emoji e7">🍗</span>
    <span class="emoji e8">🥗</span>
</div>
<div class="hero">
    <h1>🍽️ SwiftFood</h1>
    <p class="tagline">Delicious food delivered to your doorstep</p>
    <p class="sub-tagline">
        Order from the best restaurants · Fast delivery · Great prices
    </p>
    <a href="login.jsp" class="btn">Login</a>
    <a href="register.jsp" class="btn btn-outline">Register</a>
</div>
</body>
</html>