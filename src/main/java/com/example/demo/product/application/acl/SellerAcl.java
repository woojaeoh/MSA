package com.example.demo.product.application.acl;

import java.util.UUID;

public interface SellerAcl {
    SellerIdentity loadActiveSeller(UUID sellerId);
}
