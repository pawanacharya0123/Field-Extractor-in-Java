#batch 		: 	{  id, customer_id, type, received_date, status, completed_date  }
#document 	: 	{  id, batch_id, status, insured_name, insured_address, claimed_charge}

#1) Find total claimed_charge of the exported documents.

Select sum(claimed_charge) from document where status= 'EXPORTED'

#2) Find insured_name, insured_address and claimed_charge for the documents that have status "TO_REPRICE" and customer id 1 and 2.

Select insured_name, insured_address, claimed_charge
from document inner join batch
on document.batch_id= batch.id
where document.status= 'TO_REPRICE' and 
	(batch.customer_id= 1 or batch.customer_id= 2)