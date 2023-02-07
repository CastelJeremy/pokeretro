CREATE TABLE public.bosses (
    id uuid PRIMARY KEY,
    name VARCHAR(16) NOT NULL,
    id_next_boss uuid DEFAULT NULL,
    FOREIGN KEY (id_next_boss) REFERENCES bosses(id)
);

CREATE TABLE public.trainer_boss (
    id_trainer uuid PRIMARY KEY,
    id_boss uuid DEFAULT NULL,
    FOREIGN KEY (id_boss) REFERENCES bosses(id)
);

INSERT INTO bosses VALUES ('6ffe8eb1-642f-4e93-a827-0caff9af0825', 'Brock', 'cc652400-79c6-4c09-9271-a034582a7383'),
('cc652400-79c6-4c09-9271-a034582a7383', 'Misty', 'e1bf2150-992b-4e3e-85fe-c3c2b8b2bad7'),
('e1bf2150-992b-4e3e-85fe-c3c2b8b2bad7', 'Lt. Surge', 'd4723e0f-40a3-4bec-90a7-64b1405d0c0e'),
('d4723e0f-40a3-4bec-90a7-64b1405d0c0e', 'Erika', '3635cee5-2b35-4e76-8ea8-4b80200e7a5f'),
('3635cee5-2b35-4e76-8ea8-4b80200e7a5f', 'Koga', '0d793293-e0b1-477e-adaf-c9da1d64c8f1'),
('0d793293-e0b1-477e-adaf-c9da1d64c8f1', 'Sabrina', 'fecced00-ae00-4bf7-ae31-7d40dfe4b26d'),
('fecced00-ae00-4bf7-ae31-7d40dfe4b26d', 'Blaine', 'ff21b0b3-3216-4402-b879-ace7956d7cc3'),
('ff21b0b3-3216-4402-b879-ace7956d7cc3', 'Giovanni', '7dad45d0-e9f4-4af4-a589-a924f9449edf'),
('7dad45d0-e9f4-4af4-a589-a924f9449edf', 'Lorelei', '30f7a65f-fbcb-4bf1-ae3e-3d8a61a57f0e'),
('30f7a65f-fbcb-4bf1-ae3e-3d8a61a57f0e', 'Bruno', 'fd6635b7-6242-49e0-a1f8-36337fed6184'),
('fd6635b7-6242-49e0-a1f8-36337fed6184', 'Agatha', 'c18eb388-d815-452c-8792-6562a74151d5'),
('c18eb388-d815-452c-8792-6562a74151d5', 'Lance', 'e8ad7773-c2c4-41b4-956a-c5ebbb8bfb83'),
('e8ad7773-c2c4-41b4-956a-c5ebbb8bfb83', 'Blue', NULL);
