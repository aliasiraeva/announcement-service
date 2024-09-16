insert into announcement(text, lat, lng, phone_number, date)
values('Вчера вечером пропала шотландская вислоухая кошка. ' ||
       'В последний раз видели около автобусной остановки. Девочку зовут Маркиза. ' ||
       'Окрас серый, глаза ярко-янтарные. Маркиза очень пугливая и стеснительная. ' ||
       'Доброго человека, нашедшего нашу девочку, ждет хорошее вознаграждение.',
       56.19, 44.0, '9777977797', '2024-09-07');

insert into image(image_link, is_preview, announcement_id)
values('first_image', false, 2),
      ('second_image', true, 2),
      ('third_image', false, 2);