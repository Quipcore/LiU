clc

A = [1 2 3; 3 8 10];
B = [7; 4; 5];
C = [3 2 1];

%B(3)
%A(2,3)
%A(:,2)
%A(1,:)
%A*B; % -> ans = 30
     %          103

%A*C % -> incorrecta matris dimensioner
     
%H = ones(3,2); % En matris med dimensioner 3y 3x med bara ettor
%G = zeros(1,4); % en rad med fyra kolumner med endast nollor
%B = eye(3); % 3x3 matris med ettor och var tredje är 1
%y = 3:9; % alla heltal 3 <= x <= 9
%x = 1:4:21; % Fyra mellan varje generat tal 
%z = 10: -0.5: 7; % tal från 10 till 7 med 0.5 dekrment i mellan

%index = [1 4 5]; % Skapar en matris med nummer 1 4 och 5
%y(index); % returnerar talet vid index som bestäms i var deklareringen
%A(2,2:3); % ger 8 10?????


A = ones(4,4);
D = [A [B;20]] % Lägger b matrisen brevid a
D = ones(1,4)*2;
E = [D; ones(1,4)]; % Lägger en rad med ettor under D

x = 0:0.1:1;
cos(x).^2./x;

%A*B% -> Matris multiplkation
%A.*B% -> standard multiplikation

