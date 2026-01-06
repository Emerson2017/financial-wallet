CREATE TABLE public.wallet (
	id int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	code uuid NOT NULL,
	user_id int8 NOT NULL,
	currency varchar(10) NOT NULL,
	created_at timestamptz DEFAULT now() NOT NULL,
	CONSTRAINT wallet_pkey PRIMARY KEY (id)
);

CREATE TYPE public.pix_key_type AS ENUM (
	'EMAIL',
	'TELEFONE',
	'EVP');


CREATE TABLE public.pix_key (
	id int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	wallet_id int8 NOT NULL,
	"key_type" public.pix_key_type NOT NULL,
	"key" varchar(100) NOT NULL,
	CONSTRAINT pix_key_pkey PRIMARY KEY (id),
	CONSTRAINT fk_wallet_pix_key FOREIGN KEY (wallet_id) REFERENCES public.wallet(id)
);


CREATE TABLE public.wallet_ledger (
	id int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	wallet_id int8 NOT NULL,
	wallet_code uuid NOT NULL,
	balance_operation varchar(30) NOT NULL,
	amount numeric(19, 2) NOT NULL,
	currency bpchar(3) NOT NULL,
	movement_type varchar(30) NULL,
	event_id uuid NULL,
	occurred_at timestamptz NOT NULL,
	created_at timestamptz NOT NULL,
	CONSTRAINT wallet_ledger_pkey PRIMARY KEY (id),
	CONSTRAINT fk_wallet_ledger FOREIGN KEY (wallet_id) REFERENCES public.wallet(id)
);

CREATE INDEX idx_ledger_query_performance ON public.wallet_ledger (wallet_id, occurred_at DESC);

CREATE TABLE public.pix_transaction (
	id int8 GENERATED ALWAYS AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	end_to_end_id varchar(32) NOT NULL,
	idempotency_key uuid NOT NULL,
	from_wallet_id int8 NOT NULL,
	to_wallet_id int8 NULL,
	to_pix_key varchar(255) NOT NULL,
	amount numeric(19, 2) NOT NULL,
	currency bpchar(3) NOT NULL,
	status varchar(20) NOT NULL,
	created_at timestamptz DEFAULT now() NOT NULL,
	processed_at timestamptz NULL,
	CONSTRAINT pix_transaction_end_to_end_id_key UNIQUE (end_to_end_id),
	CONSTRAINT pix_transaction_idempotency_key_key UNIQUE (idempotency_key),
	CONSTRAINT pix_transaction_pkey PRIMARY KEY (id),
	CONSTRAINT fk_from_wallet FOREIGN KEY (from_wallet_id) REFERENCES public.wallet(id),
	CONSTRAINT fk_to_wallet FOREIGN KEY (to_wallet_id) REFERENCES public.wallet(id)
);