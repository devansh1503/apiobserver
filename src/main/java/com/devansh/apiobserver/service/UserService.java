package com.devansh.apiobserver.service;

import com.devansh.apiobserver.dto.ServiceDTO;
import com.devansh.apiobserver.dto.UserDTO;
import com.devansh.apiobserver.model.APIService;
import com.devansh.apiobserver.model.User;
import com.devansh.apiobserver.repo.ServiceRepository;
import com.devansh.apiobserver.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    public UserDTO authenticateUser(String email, String password) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                UserDTO userDTO = new UserDTO();
                userDTO.setEmail(email);
                userDTO.setFirstName(user.get().getFirstName());
                userDTO.setLastName(user.get().getLastName());
                mapServices(userDTO, user.get().getServices());
                return userDTO;
            }
        }
        return null;
    }

    public void register(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
    }

    public void createOrAddService(ServiceDTO serviceDTO, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            APIService APIService = serviceRepository.findByName(serviceDTO.getName());
            if (APIService == null) {
                APIService = new APIService();
                APIService.setName(serviceDTO.getName());
                APIService.setDescription(serviceDTO.getDescription());
                serviceRepository.save(APIService);
            }
            user.get().getServices().add(APIService);
        }
    }

    public List<ServiceDTO> getAllServices() {
        List<ServiceDTO> services = new ArrayList<>();
        for (APIService service: serviceRepository.findAll()){
            ServiceDTO serviceDTO = new ServiceDTO();
            serviceDTO.setName(service.getName());
            serviceDTO.setDescription(service.getDescription());
            services.add(serviceDTO);
        }
        return services;
    }

    public void mapServices(UserDTO userDTO, List<APIService> APIServices) {
        List<String>serviceString = new ArrayList<>();
        for (APIService APIService : APIServices) {
            serviceString.add(APIService.getName());
        }
        userDTO.setServices(serviceString);
    }
}
