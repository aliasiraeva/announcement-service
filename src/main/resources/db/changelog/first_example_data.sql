insert into announcement(text, lat, lng, phone_number, date)
values('Пропал щенок породы лайка. Окрас рыжий с белым брюшком. ' ||
       'Возраст около полугода. Отзывается на кличку Персик, легко идет на контакт.' ||
       'Нашедшего ждет вознаграждение. Просьба звонить по указанному номеру.',
       55.47, 49.7, '9673929000', '2024-09-05');

insert into image(image_link, is_preview, announcement_id)
values('first_image', true, 1),
      ('second_image', false, 1);