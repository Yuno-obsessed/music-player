package sanity.nil.musicservice.presentation.grpc;


import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import sanity.nil.generated.grpc.SongItemQuery;
import sanity.nil.generated.grpc.SongID;
import sanity.nil.generated.grpc.CreateSong;
import sanity.nil.generated.grpc.CreatedSong;
import sanity.nil.generated.grpc.AuthorID;
import sanity.nil.generated.grpc.AlbumID;
import sanity.nil.generated.grpc.PlaylistID;
import sanity.nil.generated.grpc.MusicServiceGrpc;
import sanity.nil.generated.grpc.SongName;
import sanity.nil.generated.grpc.SongQuery;
import sanity.nil.musicservice.application.dto.command.CreateSongCommandDTO;
import sanity.nil.musicservice.application.dto.query.SongItemQueryDTO;
import sanity.nil.musicservice.application.dto.query.SongQueryDTO;
import sanity.nil.musicservice.application.dto.response.CreatedSongDTO;
import sanity.nil.musicservice.application.service.SongCommandService;
import sanity.nil.musicservice.application.service.SongQueryService;
import sanity.nil.musicservice.infrastructure.database.mapper.SongMapper;

import java.util.List;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class GrpcServer extends MusicServiceGrpc.MusicServiceImplBase {

    private final SongQueryService songQueryService;
    private final SongCommandService songCommandService;

    @Override
    public void create(CreateSong request, StreamObserver<CreatedSong> responseObserver) {
        CreateSongCommandDTO command = SongMapper.convertPresentationToCreateCommand(request);
        CreatedSongDTO dto = songCommandService.createSongCommand.handle(command);

        responseObserver.onNext(CreatedSong.newBuilder().setId(dto.id.toString()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void findByID(SongID request, StreamObserver<SongQuery> responseObserver) {
        SongQueryDTO dto = songQueryService.getSongByIDQuery.handle(UUID.fromString(request.getId()));

        SongQuery response = SongMapper.convertSongQueryToPresentation(dto);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findByName(SongName request, StreamObserver<SongItemQuery> responseObserver) {
        List<SongItemQueryDTO> dtos = songQueryService.getSongsByTitleQuery.handle(request.getName());

        for (SongItemQueryDTO dto : dtos) {
            SongItemQuery response = SongMapper.convertSongItemQueryToPresentation(dto);
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void findByAuthor(AuthorID request, StreamObserver<SongItemQuery> responseObserver) {
        super.findByAuthor(request, responseObserver);
    }

    @Override
    public void findByAlbum(AlbumID request, StreamObserver<SongItemQuery> responseObserver) {
        super.findByAlbum(request, responseObserver);
    }

    @Override
    public void findByPlaylist(PlaylistID request, StreamObserver<SongItemQuery> responseObserver) {
        super.findByPlaylist(request, responseObserver);
    }
}
