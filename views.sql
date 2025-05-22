create
materialized view accommodations_per_host as
select h.id as host_id, count(a.id) as num_accommodations
from host h
         left join accommodation a on h.id = a.host_id
group by h.id;

create
materialized view hosts_per_country as
select c.id as country_id, count(h.id) as num_hosts
from country c
         left join host h on c.id = h.country_id
group by c.id;