package com.hotmart.api.company.services.business;

import com.hotmart.api.company.controller.form.AddressForm;
import com.hotmart.api.company.controller.vo.AddressVo;
import com.hotmart.api.company.data.AddressDataFactory;
import com.hotmart.api.company.model.entity.Address;
import com.hotmart.api.company.model.exception.GenericErrorException;
import com.hotmart.api.company.model.exception.ResourceNotFoundException;
import com.hotmart.api.company.model.mapper.AddressMapper;
import com.hotmart.api.company.model.mapper.AddressMapperImpl;
import com.hotmart.api.company.repository.AddressRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    private AddressService addressService;

    private AddressMapper addressMapper;

    @Mock
    private AddressRepository addressRepository;

    @Before
    public void init(){
        addressMapper = new AddressMapperImpl();
        addressService = new AddressService(addressMapper, addressRepository);
    }

    @Test
    public void findAllShouldReturnListAddressDtoResponseWith3Elements(){
        final List<Address> addressList = new ArrayList<>();
        addressList.add(AddressDataFactory.buildAddress(1L));
        addressList.add(AddressDataFactory.buildAddress(2L));
        addressList.add(AddressDataFactory.buildAddress(3L));
        Mockito.when(addressRepository.findAll()).thenReturn(addressList);

        final List<AddressVo> addressListDtoResponse = addressService.findAll();

        Assert.assertTrue(addressListDtoResponse.size() == 3);
        Assert.assertEquals(addressList.get(0).getStreet(), addressListDtoResponse.get(0).getStreet());
        Assert.assertEquals(addressList.get(0).getCity(), addressListDtoResponse.get(0).getCity());
        Assert.assertEquals(addressList.get(0).getCountry(), addressListDtoResponse.get(0).getCountry());
        Assert.assertEquals(addressList.get(0).getState(), addressListDtoResponse.get(0).getState());
        Assert.assertEquals(addressList.get(0).getZipCode(), addressListDtoResponse.get(0).getZipCode());

        Assert.assertEquals(addressList.get(1).getStreet(), addressListDtoResponse.get(1).getStreet());
        Assert.assertEquals(addressList.get(2).getStreet(), addressListDtoResponse.get(2).getStreet());
    }

    @Test
    public void findAllShouldReturnListEmpty(){
        Mockito.when(addressRepository.findAll()).thenReturn(new ArrayList<>());

        final List<AddressVo> addressListDtoResponse = addressService.findAll();

        Assert.assertNotNull(addressListDtoResponse);
        Assert.assertTrue(addressListDtoResponse.isEmpty());
    }

    @Test
    public void createShouldReturnAddressDtoResponse() throws GenericErrorException {
        final AddressForm addressForm = AddressDataFactory.buildAddressDtoRequest(1L);
        Mockito.when(addressRepository.save(Mockito.any())).thenReturn(addressMapper.toAddress(addressForm));

        final AddressVo addressVo = addressService.create(addressForm);

        Assert.assertEquals(addressForm.getStreet(), addressVo.getStreet());
        Assert.assertEquals(addressForm.getCity(), addressVo.getCity());
        Assert.assertEquals(addressForm.getCountry(), addressVo.getCountry());
        Assert.assertEquals(addressForm.getState(), addressVo.getState());
        Assert.assertEquals(addressForm.getZipCode(), addressVo.getZipCode());
    }

    @Test
    public void updateShouldReturnAddressDtoResponse() throws ResourceNotFoundException {
        final Optional<Address> optionalAddress = Optional.of(AddressDataFactory.buildAddress(1L));
        Mockito.when(addressRepository.findById(1L)).thenReturn(optionalAddress);

        final AddressForm addressForm = AddressDataFactory.buildAddressDtoRequest(2L);

        final AddressVo addressVo = addressService.update(1L, addressForm);

        Assert.assertEquals(addressForm.getStreet(), addressVo.getStreet());
        Assert.assertEquals(addressForm.getCity(), addressVo.getCity());
        Assert.assertEquals(addressForm.getCountry(), addressVo.getCountry());
        Assert.assertEquals(addressForm.getState(), addressVo.getState());
        Assert.assertEquals(addressForm.getZipCode(), addressVo.getZipCode());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateShouldReturnResourceNotFoundException() throws ResourceNotFoundException {
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        addressService.update(1L, null);
    }

    @Test
    public void deleteShouldReturnTrue() throws ResourceNotFoundException {
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(new Address()));

        Assert.assertTrue(addressService.delete(1L));
    }


    @Test(expected = ResourceNotFoundException.class)
    public void deleteShouldReturnResourceNotFoundException() throws ResourceNotFoundException {
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.empty());

       addressService.delete(1L);
    }

    @Test
    public void findByIdShouldReturnAddressDtoResponse() throws ResourceNotFoundException {
        final Address address = AddressDataFactory.buildAddress(1L);
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        final AddressVo addressVo = addressService.findById(1L);

        Assert.assertEquals(address.getStreet(), addressVo.getStreet());
        Assert.assertEquals(address.getZipCode(), addressVo.getZipCode());
        Assert.assertEquals(address.getState(), addressVo.getState());
        Assert.assertEquals(address.getCountry(), addressVo.getCountry());
        Assert.assertEquals(address.getCity(), addressVo.getCity());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByIdShouldReturnResourceNotFoundExceptionl() throws ResourceNotFoundException {
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        addressService.findById(1L);
    }


}
