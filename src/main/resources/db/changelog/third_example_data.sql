insert into announcement(text, lat, lng, phone_number, date)
values('Пропала девочка ротвейлер по кличке Зефирка. Фото приложено выше. ' ||
       'Звонить по номеру телефона или писать в WhatsApp',
       55.51, 48.31, '9999900000', '2024-09-08');

insert into image(image_link, is_preview, announcement_id)
values ('image', true, 3);