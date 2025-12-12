package com.easyoffer.offer_integration.service;

import com.easyoffer.offer_client.client.OfferClient;
import com.easyoffer.offer_client.to.OfferTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferClient offerClient;

    public List<OfferTO> getAll() {
        return offerClient.getOffers();
    }

}
