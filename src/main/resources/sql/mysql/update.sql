

INSERT INTO inventory
    (item_id, product_name, sales_price, description, menu_img)
VALUES
(1, 'The Full Spread', 10.15, 'We were unsure what to do with the rest of our food.  So we decided to throw it all in the fryer.  Wow is it good!', LOAD_FILE('image/inventory/01_the_full_spread.jpg')),
(2, 'Smoked brisket', 17.69, 'Smoked with Sandalwood chips, this is the most juicy, mouthwatering brisket is the best thing you will eat.  Ever.', LOAD_FILE('image/inventory/02_smoked_brisket.jpg')),
(3, 'Pulled pork', 6.35, 'The same great flavor as our Pork Ribs!', LOAD_FILE('image/inventory/03_pulled_pork.jpg')),
(4, 'Pork Ribs', 10.81, 'Our famous fall-off-the-bone Baked Ribs.  This is the dish that started it all', LOAD_FILE('image/inventory/04_pork_ribs.jpg')),
(5, 'Beef Ribs', 27.35, 'Mammoth mouthfuls of adorned meat.  The Best Stuff.', LOAD_FILE('image/inventory/05_beef_ribs.jpg')),
(6, 'Jerked Chicken', 22.15, 'Your choice of 10 or 20 Blazing Hot Buffalo, BBQ, Sweet Chilli, Teriyaki, or Plain.  Accompanied  with Ranch or Blue Cheese dressing.', LOAD_FILE('image/inventory/06_jerked_chicken.jpg')),
(7, 'Coleslaw', 2.47, 'With apple cider vinegar and a sprinkle of sweetness, this tangy Southern style coleslaw will knock your socks off', LOAD_FILE('image/inventory/07_coleslaw.jpg'))
ON DUPLICATE KEY
UPDATE
    item_id=item_id,
    product_name = product_name,
    sales_price = sales_price,
    description = description,
    menu_img = menu_img;

# The Full Spread|Smoked brisket|Pulled pork|Pork Ribs|Beef Ribs|Jerked Chicken|Coleslaw